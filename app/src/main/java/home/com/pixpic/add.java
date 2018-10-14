package home.com.pixpic;

import android.content.ContentValues;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class add extends AppCompatActivity {

    private EditText edname;
    private EditText edpx;
    private EditText edpy;
    private EditText edcolr;
    private EditText edcolg;
    private EditText edcolb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        edname = findViewById(R.id.et_name);
        edpx = findViewById(R.id.et_px);
        edpy = findViewById(R.id.et_py);
        edcolr = findViewById(R.id.et_r);
        edcolg = findViewById(R.id.et_g);
        edcolb = findViewById(R.id.et_b);
    }
    public void add (View view){
        String name = edname.getText().toString();
        int px = Integer.parseInt(edpx.getText().toString());
        int py = Integer.parseInt(edpy.getText().toString());
        int r = Integer.parseInt(edcolr.getText().toString());
        int g = Integer.parseInt(edcolg.getText().toString());
        int b = Integer.parseInt(edcolb.getText().toString());
        PixpicHelper helper = new PixpicHelper(this);
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("PX",px);
        values.put("PY",py);
        values.put("R",r);
        values.put("G",g);
        values.put("B",b);
        long id = helper.getWritableDatabase().insert("PIXPIC",null,values);
        if(id>-1){
            Toast.makeText(this,"新增成功",Toast.LENGTH_LONG).show();
        }
        else {Toast.makeText(this,"新增失敗",Toast.LENGTH_LONG).show();}
    }
}
