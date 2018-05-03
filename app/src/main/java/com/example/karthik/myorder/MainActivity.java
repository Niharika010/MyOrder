package com.example.karthik.myorder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_TAG = "MainActivity";
    final int BASEPRICE = 5;
    final int VEG_PRICE = 1;
    final int NON_VEG_PRICE = 2;
    final int CHEESE_PRICE = 1;
    final int D_CHEESE_PRICE = 1;
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

       /* Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:47.6,-122.3"));
        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        }*/

//        get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();
//        check if whipped cream is selected

        /*CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checked);
        boolean hasWhippedCream = whippedCream.isChecked();
        //        check if chocolate is selected
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checked);
        boolean hasChocolate = chocolate.isChecked();*/

        CheckBox veg = (CheckBox) findViewById(R.id.veg_checked);
        boolean hasVeg = veg.isChecked();

        CheckBox nonveg = (CheckBox) findViewById(R.id.nonveg_checked);
        boolean hasNonVeg = nonveg.isChecked();

        CheckBox cheese = (CheckBox) findViewById(R.id.cheese_checked);
        boolean hasCheese = cheese.isChecked();

        CheckBox dCheese = (CheckBox) findViewById(R.id.dcheese);
        boolean hasDCheese = dCheese.isChecked();






//        calculate and store the total price
        float totalPrice = calculatePrice(hasVeg,hasNonVeg,hasCheese,hasDCheese);
//        create and store the order summary
        String orderSummaryMessage = createOrderSummary(userInputName, hasVeg,hasNonVeg,hasCheese,hasDCheese, totalPrice);
// Write the relevant code for making the buttons work(i.e impelement the implicit and explicit intents


        /* Create the Intent */
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        /* Fill it with Data */
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"to@email.com"});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Pizza");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, orderSummaryMessage);

        /* Send it off to the Activity-Chooser */
        this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));



    }
    private String boolToString(boolean bool){
        return bool?(getString(R.string.yes)):(getString(R.string.no));

    }

    private String createOrderSummary(String userInputName, boolean hasVeg,boolean hasNonVeg, boolean hasCheese,boolean hasDCheese, float price) {
        String orderSummaryMessage = getString(R.string.order_summary_name,userInputName) +"\n"+
                getString(R.string.order_summary_veg,boolToString(hasVeg))+"\n"+
                getString(R.string.order_summary_nonveg,boolToString(hasNonVeg)) +"\n"+
                getString(R.string.order_summary_cheese,boolToString(hasCheese))+"\n"+
                getString(R.string.order_summary_dcheese,boolToString(hasDCheese)) +"\n"+
                getString(R.string.order_summary_quantity,quantity)+"\n"+
                getString(R.string.order_summary_total_price,price) +"\n"+
                getString(R.string.thank_you);
        return orderSummaryMessage;

    }


    /**
     * Method to calculate the total price
     *
     * @return total Price
     */
    private float calculatePrice(boolean hasveg, boolean hasNonVeg , boolean hasCheese, boolean hasDcheese) {
        int basePrice = BASEPRICE;
        if (hasveg) {
            basePrice += VEG_PRICE;
        }
        if (hasNonVeg) {
            basePrice += NON_VEG_PRICE;
        }

        if(hasCheese){
            basePrice += CHEESE_PRICE;
        }


        if(hasDcheese){
            basePrice += D_CHEESE_PRICE;
        }

        return quantity * basePrice;

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }


    /**
     * This method increments the quantity of coffee cups by one
     *
     * @param view on passes the view that we are working with to the method
     */

    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        } else {

            Log.i("MainActivity", "Please select less than one hundred cups of pizza");
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_much_pizza);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;

        }
    }

    /**
     * This method decrements the quantity of coffee cups by one
     *
     * @param view passes on the view that we are working with to the method
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {

            Log.i("MainActivity", "Please select atleast one cup of pizza");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_little_pizza);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;


        }
    }
}
