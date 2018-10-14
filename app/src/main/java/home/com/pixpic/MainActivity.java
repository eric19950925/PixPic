package home.com.pixpic;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private PixpicHelper helper;
    private PixpicAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,add.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        helper = new PixpicHelper(this);
        Cursor cursor = helper.getReadableDatabase()
                .query("Pixpic",null,null,null,null,null,null);
        adapter = new PixpicAdapter(cursor);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Cursor cursor = helper.getReadableDatabase()
                .query("Pixpic",null,null,null,null,null,null);
        adapter = new PixpicAdapter(cursor);
        recyclerView.setAdapter(adapter);
    }

    public class PixpicAdapter extends RecyclerView.Adapter<PixpicAdapter.PixpicHolder>{
        Cursor cursor;
        public PixpicAdapter(Cursor cursor) {
            this.cursor = cursor;
        }

        @NonNull
        @Override
        public PixpicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.pix_item,parent,false);

            return new PixpicHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PixpicHolder holder, int position) {
            cursor.moveToPosition(position);
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int px = cursor.getInt(cursor.getColumnIndex("PX"));
            int py = cursor.getInt(cursor.getColumnIndex("PY"));
            int r = cursor.getInt(cursor.getColumnIndex("R"));
            int g = cursor.getInt(cursor.getColumnIndex("G"));
            int b = cursor.getInt(cursor.getColumnIndex("B"));
            holder.nametext.setText(name);
            holder.pxtext.setText(String.valueOf(px));
            holder.pytext.setText(String.valueOf(py));
            holder.rtext.setText(String.valueOf(r));
            holder.gtext.setText(String.valueOf(g));
            holder.btext.setText(String.valueOf(b));
        }

        @Override
        public int getItemCount() {
            return cursor.getCount();
        }

        public class PixpicHolder extends RecyclerView.ViewHolder{
            TextView nametext;
            TextView pxtext;
            TextView pytext;
            TextView rtext;
            TextView gtext;
            TextView btext;
            public PixpicHolder(View itemView){
                super (itemView);
                nametext = itemView.findViewById(R.id.tv_name);
                pxtext = itemView.findViewById(R.id.tv_px);
                pytext = itemView.findViewById(R.id.tv_py);
                rtext = itemView.findViewById(R.id.tv_r);
                gtext = itemView.findViewById(R.id.tv_g);
                btext = itemView.findViewById(R.id.tv_b);
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
