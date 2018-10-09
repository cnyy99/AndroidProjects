package bjfu.it.chennan.beeradivser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.List;
public class FindBeerActivity extends AppCompatActivity {
    private BeerExpert expert = new BeerExpert();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_beer);
    }
    public void onClickFindBeer(View view){
        TextView brands = (TextView) findViewById(R.id.brands);
        Spinner color = (Spinner) findViewById(R.id.color);
        String beerType = String.valueOf(color.getSelectedItem());
        List<String> BrandsList = expert.getBrands(beerType);
        StringBuilder brandsFormtted = new StringBuilder();
        for(String brand: BrandsList)
        {
            brandsFormtted.append(brand).append("\n");
        }
        brands.setText(brandsFormtted);
    }
}
