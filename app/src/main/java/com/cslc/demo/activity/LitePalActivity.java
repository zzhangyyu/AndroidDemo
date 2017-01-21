package com.cslc.demo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cslc.demo.R;
import com.cslc.demo.bean.BookBean;

import org.litepal.tablemanager.Connector;

/**
 * Created by Admin on 2017/1/14.
 */

public class LitePalActivity extends BaseActivity implements View.OnClickListener {
    private Button createDBBtn;
    private Button insertDBBtn;
    private Button updateDB;
    private Button queryDB;
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_litepal;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createDBBtn= (Button) findViewById(R.id.create_db);
        insertDBBtn= (Button) findViewById(R.id.insert_db);
        createDBBtn.setOnClickListener(this);
        insertDBBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==createDBBtn.getId()){
            Connector.getDatabase();
        }else if(v.getId()==insertDBBtn.getId()){
            BookBean book =new BookBean();
            book.setAuthor("David");
            book.setName("达芬奇密码");
            book.setPages(500);
            book.setPrice(25.00);
            book.save();
        }
    }
}
