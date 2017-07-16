package io.github.dmitrikudrenko.demofeature.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.github.dmitrikudrenko.demofeature.DemoApplication;
import io.github.dmitrikudrenko.demofeature.R;
import io.github.dmitrikudrenko.demofeature.cache.model.Item;
import io.github.dmitrikudrenko.demofeature.data.DataProvider;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {
    public static Intent intent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Inject
    DataProvider dataProvider;

    private ItemsAdapter adapter = new ItemsAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DemoApplication.injectionComponent.inject(this);
        injectViews();
        dataProvider.getItems()
                .subscribe(new Action1<List<Item>>() {
                    @Override
                    public void call(List<Item> items) {
                        adapter.notifyDataSetChanged(items);
                    }
                });
    }

    private void injectViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.m_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.exit) {
            startActivity(EnterActivity.intent(this));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private static class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemHolder> {
        private List<Item> items = new ArrayList<>();

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.v_item, parent, false));
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            holder.setName(items.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public void notifyDataSetChanged(List<Item> items) {
            this.items = items;
            notifyDataSetChanged();
        }

        class ItemHolder extends RecyclerView.ViewHolder {
            private TextView nameView;

            ItemHolder(View itemView) {
                super(itemView);
                nameView = (TextView) itemView;
            }

            public void setName(String name) {
                nameView.setText(name);
            }
        }
    }
}
