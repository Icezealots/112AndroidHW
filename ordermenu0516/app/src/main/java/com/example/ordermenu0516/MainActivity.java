package com.example.ordermenu0516;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    private Spinner spinnerMenu;
    private TextView tvMainCourse, tvSideDish, tvDrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinnerMenu = findViewById(R.id.spinner_menu);
        tvMainCourse = findViewById(R.id.tv_main_course);
        tvSideDish = findViewById(R.id.tv_side_dish);
        tvDrink = findViewById(R.id.tv_drink);

        // 將選項添加到 Spinner 中
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.menu_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMenu.setAdapter(adapter);

        // 設置 Spinner 的監聽器
        spinnerMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 根據選擇的項目顯示相應的按鈕
                switch (position) {
                    case 0: // 主餐
                        showMainCourseButtons();
                        hideSideDishButtons();
                        hideDrinkButtons();
                        break;
                    case 1: // 附餐
                        hideMainCourseButtons();
                        showSideDishButtons();
                        hideDrinkButtons();
                        break;
                    case 2: // 飲料
                        hideMainCourseButtons();
                        hideSideDishButtons();
                        showDrinkButtons();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 如果未選擇任何項目，則不執行任何操作
            }
        });

        // 設置按鈕的點擊監聽器
        findViewById(R.id.tv_main_course1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMainCourse.setText("主餐: 牛肉漢堡");
            }
        });

        findViewById(R.id.tv_main_course2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMainCourse.setText("主餐: 豬肉漢堡");
            }
        });

        findViewById(R.id.tv_main_course3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMainCourse.setText("主餐: 炸雞漢堡");
            }
        });

        findViewById(R.id.tv_main_course4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMainCourse.setText("主餐: 炸魚漢堡");
            }
        });

        // 設置附餐按鈕的點擊監聽器
        findViewById(R.id.tv_side_dish1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSideDish.setText("附餐: 薯條");
            }
        });

        findViewById(R.id.tv_side_dish2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSideDish.setText("附餐: 沙拉");
            }
        });

        findViewById(R.id.tv_side_dish3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSideDish.setText("附餐: 玉米濃湯");
            }
        });

        findViewById(R.id.tv_side_dish4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSideDish.setText("附餐: 蘋果派");
            }
        });

// 設置飲料TextView的點擊監聽器
        findViewById(R.id.tv_drink1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDrink.setText("飲料: 可樂");
            }
        });

        findViewById(R.id.tv_drink2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDrink.setText("飲料: 雪碧");
            }
        });

        findViewById(R.id.tv_drink3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDrink.setText("飲料: 冰紅茶");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_submit) {
            // 顯示所點擊的主餐/附餐/飲料
            String mainCourse = tvMainCourse.getText().toString();
            String sideDish = tvSideDish.getText().toString();
            String drink = tvDrink.getText().toString();

            String message = "你的訂單：\n";
            if (!mainCourse.isEmpty()) {
                message += mainCourse + "\n";
            }
            if (!sideDish.isEmpty()) {
                message += sideDish + "\n";
            }
            if (!drink.isEmpty()) {
                message += drink + "\n";
            }

            // 创建 Intent 对象
            Intent intent = new Intent(MainActivity.this, AnotherActivity.class);
            // 将表单内容作为 Extra 数据传递给 AnotherActivity
            intent.putExtra("order_details", message);
            // 启动 AnotherActivity
            startActivity(intent);

            return true;
        } else if (itemId == R.id.action_cancel) {
            // 將 textview 重設為初始狀態
            tvMainCourse.setText("主餐: ");
            tvSideDish.setText("附餐: ");
            tvDrink.setText("飲料: ");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    // 顯示選單的方法
    private void showMessageDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // 點擊確定按鈕後的處理，這裡可以添加需要的邏輯
                    }
                });
        builder.create().show();
    }


    // 顯示主餐按鈕
    private void showMainCourseButtons() {
        findViewById(R.id.tv_main_course1).setVisibility(View.VISIBLE);
        findViewById(R.id.tv_main_course2).setVisibility(View.VISIBLE);
        findViewById(R.id.tv_main_course3).setVisibility(View.VISIBLE);
        findViewById(R.id.tv_main_course4).setVisibility(View.VISIBLE);
    }

    // 隱藏主餐按鈕
    private void hideMainCourseButtons() {
        findViewById(R.id.tv_main_course1).setVisibility(View.GONE);
        findViewById(R.id.tv_main_course2).setVisibility(View.GONE);
        findViewById(R.id.tv_main_course3).setVisibility(View.GONE);
        findViewById(R.id.tv_main_course4).setVisibility(View.GONE);
    }

    // 顯示附餐按鈕
    private void showSideDishButtons() {
        findViewById(R.id.tv_side_dish1).setVisibility(View.VISIBLE);
        findViewById(R.id.tv_side_dish2).setVisibility(View.VISIBLE);
        findViewById(R.id.tv_side_dish3).setVisibility(View.VISIBLE);
        findViewById(R.id.tv_side_dish4).setVisibility(View.VISIBLE);
    }

    // 隱藏附餐按鈕
    private void hideSideDishButtons() {
        findViewById(R.id.tv_side_dish1).setVisibility(View.GONE);
        findViewById(R.id.tv_side_dish2).setVisibility(View.GONE);
        findViewById(R.id.tv_side_dish3).setVisibility(View.GONE);
        findViewById(R.id.tv_side_dish4).setVisibility(View.GONE);
    }

    // 顯示飲料按鈕
    private void showDrinkButtons() {
        findViewById(R.id.tv_drink1).setVisibility(View.VISIBLE);
        findViewById(R.id.tv_drink2).setVisibility(View.VISIBLE);
        findViewById(R.id.tv_drink3).setVisibility(View.VISIBLE);
    }

    // 隱藏飲料按鈕
    private void hideDrinkButtons() {
        findViewById(R.id.tv_drink1).setVisibility(View.GONE);
        findViewById(R.id.tv_drink2).setVisibility(View.GONE);
        findViewById(R.id.tv_drink3).setVisibility(View.GONE);
    }
}