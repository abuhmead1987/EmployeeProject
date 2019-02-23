package com.examples.android.employeeproject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.examples.android.employeeproject.Utils.Utils;
import com.examples.android.employeeproject.models.Employee;

import java.util.Calendar;

/**
 * A fragment representing a single Employee detail screen.
 * This fragment is either contained in a {@link EmployeeListActivity}
 * in two-pane mode (on tablets) or a {@link EmployeeDetailActivity}
 * on handsets.
 */
public class EmployeeDetailFragment extends Fragment implements TextWatcher, DatePickerDialog.OnDateSetListener {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    private TextView txtvu_hireDate;
    /**
     * The dummy content this fragment is presenting.
     */
    private Employee mItem;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EmployeeDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            int empIndex = getArguments().getInt(ARG_ITEM_ID);
            mItem = MainActivity.employeeLinkedList.get(empIndex);
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getName());

            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.employee_info_edit, container, false);

        txtvu_hireDate = ((TextView) rootView.findViewById(R.id.edt_date));
        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((ImageView) getActivity().findViewById(R.id.imgvu_emp_pic)).setImageResource(mItem.getPicResID());
            ((EditText) rootView.findViewById(R.id.edt_name)).setText(mItem.getName());
            ((EditText) rootView.findViewById(R.id.edt_email)).setText(mItem.getEmail());
            ((EditText) rootView.findViewById(R.id.edt_phone)).setText(mItem.getPhone());
            ((EditText) rootView.findViewById(R.id.edt_address)).setText(mItem.getAddress());
            txtvu_hireDate.setText(Utils.getDateFormatedString(mItem.getHireDate()));
        }
        txtvu_hireDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year, month, day;
                String[] date = txtvu_hireDate.getText().toString().split("\\.");
                if (date.length != 3) {
                    Calendar c = Calendar.getInstance();
                    year = c.get(Calendar.YEAR);
                    month = c.get(Calendar.MONTH);
                    day = c.get(Calendar.DAY_OF_MONTH);
                } else {
                    year = Integer.parseInt(date[2]);
                    month = Integer.parseInt(date[1]);
                    day = Integer.parseInt(date[0]);
                }

                // Create a new instance of DatePickerDialog and return it.
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), EmployeeDetailFragment.this, year, month, day);
                datePickerDialog.show();
            }
        });

        return rootView;
    }

    /**
     * This method is called to notify you that, within <code>s</code>,
     * the <code>count</code> characters beginning at <code>start</code>
     * are about to be replaced by new text with length <code>after</code>.
     * It is an error to attempt to make changes to <code>s</code> from
     * this callback.
     *
     * @param s
     * @param start
     * @param count
     * @param after
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    /**
     * This method is called to notify you that, within <code>s</code>,
     * the <code>count</code> characters beginning at <code>start</code>
     * have just replaced old text that had length <code>before</code>.
     * It is an error to attempt to make changes to <code>s</code> from
     * this callback.
     *
     * @param s
     * @param start
     * @param before
     * @param count
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    /**
     * This method is called to notify you that, somewhere within
     * <code>s</code>, the text has been changed.
     * It is legitimate to make further changes to <code>s</code> from
     * this callback, but be careful not to get yourself into an infinite
     * loop, because any changes you make will cause this method to be
     * called again recursively.
     * (You are not told where the change took place because other
     * afterTextChanged() methods may already have made other changes
     * and invalidated the offsets.  But if you need to know here,
     * you can use {@link Spannable#setSpan} in {@link #onTextChanged}
     * to mark your place and then look up from here where the span
     * ended up.
     *
     * @param s
     */
    @Override
    public void afterTextChanged(Editable s) {

    }

    /**
     * @param view       the picker associated with the dialog
     * @param year       the selected year
     * @param month      the selected month (0-11 for compatibility with
     *                   {@link Calendar#MONTH})
     * @param dayOfMonth th selected day of the month (1-31, depending on
     */
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        txtvu_hireDate.setText("" + dayOfMonth + "." + (++month) + "." + year);
    }
}
