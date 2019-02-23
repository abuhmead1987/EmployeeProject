package com.examples.android.employeeproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.examples.android.employeeproject.Utils.Utils;
import com.examples.android.employeeproject.models.Employee;

/**
 * An activity representing a single Employee detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link EmployeeListActivity}.
 */
public class EmployeeDetailActivity extends AppCompatActivity {
    private EmployeeDetailFragment fragment = null;
    private int empListIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragment != null) {
                    View rootView = fragment.getView();
                    Employee emp;
                    if(empListIndex==-1){
                        emp=new Employee();
                        emp.setPicResID(R.drawable.emp_pic7);
                        MainActivity.employeeLinkedList.add(emp);
                    }

                    else {
                        emp = MainActivity.employeeLinkedList.get(empListIndex);
                    }
                    emp.setName(((EditText) rootView.findViewById(R.id.edt_name)).getText().toString());
                    emp.setEmail(((EditText) rootView.findViewById(R.id.edt_email)).getText().toString());
                    emp.setPhone(((EditText) rootView.findViewById(R.id.edt_phone)).getText().toString());
                    emp.setAddress(((EditText) rootView.findViewById(R.id.edt_address)).getText().toString());
                    emp.setHireDate(Utils.getDateFromString(((TextView) rootView.findViewById(R.id.edt_date)).getText().toString()));

                }
                Snackbar.make(view, "Employee Info Saved...", Snackbar.LENGTH_LONG).show();
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            empListIndex = getIntent().getIntExtra(EmployeeDetailFragment.ARG_ITEM_ID, 0);
            arguments.putInt(EmployeeDetailFragment.ARG_ITEM_ID, empListIndex);
            fragment = new EmployeeDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.employee_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, EmployeeListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
