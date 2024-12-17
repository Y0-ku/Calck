package com.example.myapplication
//Импорт библеотек для работы
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
//объявление переменных
    private lateinit var etNum1: EditText
    private lateinit var etNum2: EditText
    private lateinit var btnAdd: AppCompatButton
    private lateinit var btnSub: AppCompatButton
    private lateinit var btnMult: AppCompatButton
    private lateinit var btnDiv: AppCompatButton
    private lateinit var tvResult: TextView
    private var oper = ""

    private val MENU_RESET_ID = 1
    private val MENU_QUIT_ID = 2

    //метод открывающийся при запуске приложения
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //создание тулбара(3 точки вверху)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //привязка переменных к макету
        etNum1 = findViewById(R.id.etNum1)
        etNum2 = findViewById(R.id.etNum2)
        btnAdd = findViewById(R.id.btnAdd)
        btnSub = findViewById(R.id.btnSub)
        btnMult = findViewById(R.id.btnMult)
        btnDiv = findViewById(R.id.btnDiv)
        tvResult = findViewById(R.id.tvResult)

        btnAdd.setOnClickListener(this)
        btnSub.setOnClickListener(this)
        btnMult.setOnClickListener(this)
        btnDiv.setOnClickListener(this)
    }

    //метод определяющий на какую кнопку произведён клик
    override fun onClick(v: View) {
        var num1 = 0f
        var num2 = 0f
        var result = 0f

        //проверка на пустые поля
        if (TextUtils.isEmpty(etNum1.text.toString())
            || TextUtils.isEmpty(etNum2.text.toString())) {
            return
        }

            //Определение введенных данных
        try {
            num1 = etNum1.text.toString().toFloat()
            num2 = etNum2.text.toString().toFloat()
        } catch (e: NumberFormatException) {
            //если не числа, то вывести "Ошибка ввода"
            tvResult.text = "Ошибка ввода"
            return
        }


        //определение нажатой кнопки
        when (v.id) {
            //если на плюс, то суммировать числа
            R.id.btnAdd -> {
                oper = "+"
                result = num1 + num2
            }
            //если на минус, то вычесть числа
            R.id.btnSub -> {
                oper = "-"
                result = num1 - num2
            }
            //если на звёздочку, то умножить числа
            R.id.btnMult -> {
                oper = "*"
                result = num1 * num2
            }
            //если на слеш, то поделить числа
            R.id.btnDiv -> {
                oper = "/"
                //проверка деления на ноль
                if (num2 != 0f) {
                    result = num1 / num2
                    //если деление на ноль, вывести "Деление на ноль"
                } else {
                    tvResult.text = "Деление на ноль"
                    return
                }
            }
        }
        //вывод полученных данных в TextView (хуйня для вывода)
        tvResult.text = "$num1 $oper $num2 = ${String.format("%.2f", result)}" //доллар и переменная определяет в каком месте будет она выведена
    }
    //Создание меню(3 точки сверху), как по лабе
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
//добавление очистки и закрытия приложения в меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_reset -> {
                resetCalculator()
                return true
            }
            R.id.action_quit -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    //метод для очистки
    private fun resetCalculator() {
        etNum1.text.clear()
        etNum2.text.clear()
        tvResult.text = ""
    }
}