package br.com.senaigo.mobile.northwindtraders.activities;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import br.com.senaigo.mobile.northwindtraders.R;
import br.com.senaigo.mobile.northwindtraders.entities.Category;
import br.com.senaigo.mobile.northwindtraders.persistence.CategoryDAO;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bruno on 04/04/16.
 */
public class HomeActivity extends AppCompatActivity {


    //ButterKnife - injeção de dependência
    @BindView(R.id.txtCategoryId) public EditText txtCategoryId;
    @BindView(R.id.txtCategoryName) public EditText txtCategoryName;
    @BindView(R.id.txtCategoryDescription) public EditText txtCategoryDescription;
    @BindView(R.id.btnCategory) public Button btnCreateCategory;


    protected Category category;
    protected List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        categories = new ArrayList<Category>();
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);

    }

    protected Category populateList(){
        Category category = new Category();
        category.setCategoryId(1/*Integer.parseInt(txtCategoryId.getText().toString())*/);
        category.setCategoryName("NomeCategoria"/*txtCategoryName.getText().toString()*/);
        category.setDescription("descricao"/*txtCategoryDescription.getText().toString()*/);
        category.setPicture(BitmapFactory.decodeResource(getResources(), R.drawable.student_1));
        return category;
    }


    @OnClick(R.id.btnCategory) public void getCategory(View view) {

        ButterKnife.bind(this, view);
//        Intent it = new Intent(this, CategoryActivity.class);
//        categories.add(populateList());
//        it.putParcelableArrayListExtra("categories", (ArrayList<? extends Parcelable>) categories);
//        startActivity(it);

//        categories.add(populateList());
//        ListView listView = (ListView) findViewById(R.id.listView);
//        listView.setAdapter(new CategoryAdapter(this, categories));

        CategoryDAO categoryDAO = new CategoryDAO(this);
        categoryDAO.onInsert(populateList());

        Intent it = new Intent(this, CategoryActivity.class);
        it.putParcelableArrayListExtra("categories", (ArrayList<? extends Parcelable>) categoryDAO.onList(null));

        //fecha a conexao com o sqllite
        categoryDAO.close();

        startActivity(it);



    }

}