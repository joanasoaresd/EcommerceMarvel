package br.android.ecommerce_marvel;

import android.content.Context;
import android.widget.TextView;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import br.android.ecommerce_marvel.view.CheckoutAdapter;
import br.android.model.db.DbDatabaseComic;
import br.android.model.model.ComicsDTO;
import br.android.model.model.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CheckoutAdapterTest {

    DbDatabaseComic databaseComic = null;
    ArrayList<Item> items;
    Context context;
    TextView textView;
    CheckoutAdapter checkoutAdapter;


    @BeforeEach
    public void setUp() {
        items = new ArrayList<>();

    }

    @Test
    public void testSumTotal() {
        items.add(new Item(new ComicsDTO(0, null, null, 0, 7, null, false), 2));
        items.add(new Item(new ComicsDTO(0, null, null, 0, 12.5, null, false), 1));
        checkoutAdapter = new CheckoutAdapter(context, items, databaseComic, textView);
        assertEquals(26.5, checkoutAdapter.sumTotal(items));

    }

    @Test
    public void testSumTotalListEmpty() {
        items.isEmpty();
        checkoutAdapter = new CheckoutAdapter(context, items, databaseComic, textView);
        assertEquals(0, checkoutAdapter.sumTotal(items));

    }

    @Test
    public void testSetSumTotal() {
        Item i = new Item(new ComicsDTO(1, null, null, 0, 7, null, false), 5);
        items.add(i);
        i.setQty(2);
        checkoutAdapter = new CheckoutAdapter(context, items, databaseComic, textView);
        assertEquals(14, checkoutAdapter.sumTotal(items));
    }
}
