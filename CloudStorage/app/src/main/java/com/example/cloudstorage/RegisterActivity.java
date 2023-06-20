package com.example.cloudstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cloudstorage.models.LoggedUser;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    // System methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_view);

        // Retrieve fields
        EditText fieldsEts[] = {
                findViewById(R.id.editTextRegisterName),
                findViewById(R.id.editTextRegisterSurname1),
                findViewById(R.id.editTextRegisterSurname2),
                findViewById(R.id.editTextRegisterEmail),
                findViewById(R.id.editTextRegisterPhone),
                findViewById(R.id.editTextRegisterPassword),
                findViewById(R.id.editTextRegisterCheckPassword)
        };

        boolean invalidInput = true;
        while (invalidInput) {
            // Search for empty required values
            if (!compulsoryFieldsFilled(fieldsEts)) {
                // TODO make toast warning and do not proceed
                Toast toast = Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_SHORT);
                toast.show();
                continue;
            }

            // Search for incorrect value format
            if (!formatFieldsValid(fieldsEts)) {
                // TODO make toast warning and do not proceed
                Toast toast = Toast.makeText(this, "Los campos no tienen un formato correcto", Toast.LENGTH_SHORT);
                toast.show();
                continue;
            }

            // Ensure password value
            if (!passwordIsSecure(fieldsEts[5].getText().toString())) {
                // TODO make toast warning and do not proceed
                Toast toast = Toast.makeText(this, "La contraseña no cumple todos los requisitos", Toast.LENGTH_SHORT);
                toast.show();
                continue;
            }

            if (!passwordsMatch(fieldsEts[5].getText().toString(), fieldsEts[6].getText().toString())) {
                // TODO make toast warning and do not proceed
                Toast toast = Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT);
                toast.show();
                continue;
            }
            invalidInput = true;
        }

        // Submit data to back4app DBMS
        LoggedUser newUser = new LoggedUser();
    }

    // Custom methods
    /**
     * Checks whether a compulsory field is empty.
     * @param fields array containing all input fields.
     * @return false in case a required field is empty, true in any other case.
     */
    protected boolean compulsoryFieldsFilled(EditText fields[]) {
        for (int i = 0; i < fields.length; ++i) {
            if (i == 2) continue;
            if (fields[i].getText().toString().isEmpty()) return false;
        }
        return true;
    }

    /**
     * Check whether name, surnames, mail and phone have valid formats.
     * @param fields array containing all input fields.
     * @return false in case field has no valid format, true otherwise.
     */
    protected boolean formatFieldsValid(EditText fields[]) {
        // Check name and surnames don't contain numbers
        Pattern regex = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (regex.matcher(fields[0].getText().toString()).find()) return false;
        if (regex.matcher(fields[1].getText().toString()).find()) return false;
        if (regex.matcher(fields[2].getText().toString()).find()) return false;

        // Check if mail has a valid format
        Pattern mail_regex = Pattern.compile("[a-zA-Z0-9._\\-]*@[a-z]+.(es|com|net)");
        if (!mail_regex.matcher(fields[3].getText().toString()).matches()) return false;

        // Check if phone is valid
        Pattern phone_regex = Pattern.compile("[0-9]{9}");
        if (!phone_regex.matcher(fields[4].getText().toString()).matches()) return false;

        return true;
    }

    /**
     * Password must have at least:
     *      - 8 characters (1 lower case and 1 upper case at least)
     *      - 1 special character from the list { @, #, $, %, ^, &, +, = }
     *      - 1 digit
     *      - no whitespaces
     * @param password String to find these requirements.
     * @return true if it password meets all conditions, false otherwise
     */
    protected boolean passwordIsSecure(String password) {
        Pattern pass_regex = Pattern.compile("^(?!.*\\s)(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$");
        return pass_regex.matcher(password).matches();
    }

    /**
     * Compares both inputted passwords.
     * @param password First inputted password.
     * @param chkPassword Second inputted password.
     * @return true if they are equal, false otherwise.
     */
    protected boolean passwordsMatch(String password, String chkPassword) {
        return password.equals(chkPassword);
    }
}