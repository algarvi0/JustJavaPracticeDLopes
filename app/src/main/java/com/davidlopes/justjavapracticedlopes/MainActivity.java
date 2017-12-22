/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/
package com.davidlopes.justjavapracticedlopes;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    int pricePerCup = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayquantity(quantity);
        calculatePrice();
        //displayMessage(quantity*price);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        if(quantity < 0)
            quantity = 0;
        displayquantity(quantity);
        //displayMessage(quantity*price);
        calculatePrice();
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        String priceMessage = createOrderSumary(price);
        displayMessage(priceMessage);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayquantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * Calculates the price of the order.
     * @priceOrder returns price of the order.
     */
    private int calculatePrice() {
        return = quantity * pricePerCup;
    }

    
    /**
     * This method displays the given price on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
        //priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    /* Creates order of the order
    *
    *  @param price of the order
    *  @return text summary
    *
    */
    public String createOrderSumary(int price) {
        String priceMessage = "David Lopes \n";
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: € " + quantity * pricePerCup;
        priceMessage += "\nThank You !";
        displayMessage(priceMessage);
        return priceMessage;
    }
}