package com.yuan.lifefinance.tool

import android.Manifest
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.support.v4.content.FileProvider
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.yuan.lifefinance.tool.greendao.DBManager
import com.yuan.lifefinance.tool.services.StockPriceService
import com.yuan.lifefinance.tool.tools.*
import com.yuan.lifefinance.tool.view.CustomHintDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : BaseActivity(), PermissionTools.PermissionDealListener {


    internal var permissionlist = HashMap<String, String>()
    internal var permissionTools: PermissionTools? =null
    private var permissionIsOk: Boolean = false

    internal var rvalue: Double = 0.toDouble()

    internal var nowDate = ""

    var strs:String ?= null
    private val isDataOk: Boolean
        get() {
            if (et_name?.text.isNullOrBlank()) {
                Toast.makeText(this@MainActivity, "请输入名称！", Toast.LENGTH_SHORT).show()
                return false
            }

            if (StringInputUtils.valueIsEmpty(et_code!!) && StringInputUtils.value(et_code!!).length == 6) {
                Toast.makeText(this@MainActivity, "请输入个股代码！", Toast.LENGTH_SHORT).show()
                return false
            }
            if (StringInputUtils.valueIsEmpty(et_cost!!)) {
                Toast.makeText(this@MainActivity, "买入价格为空！", Toast.LENGTH_SHORT).show()
                return false
            }

            if (StringInputUtils.valueIsEmpty(et_stopLoss!!)) {
                Toast.makeText(this@MainActivity, "止损价格为空！", Toast.LENGTH_SHORT).show()
                return false
            }

            if (StringInputUtils.valueIsEmpty(et_mostPrice!!)) {
                Toast.makeText(this@MainActivity, "目标价格为空！", Toast.LENGTH_SHORT).show()
                return false
            }

            if (StringInputUtils.valueIsEmpty(tv_rValue!!)) {
                Toast.makeText(this@MainActivity, "R比率为空！", Toast.LENGTH_SHORT).show()
                return false
            }

            return true
        }

    init {
        permissionlist[Manifest.permission.WRITE_EXTERNAL_STORAGE] = "获取存储权限"
    }

    override fun permissionForbidden(noGrantpermissionDislist: ArrayList<String>) {
        permissionTools?.showPermissionDialog(noGrantpermissionDislist)
    }

    override fun permissionRefuse() {}

    override fun permissionPass() {
        permissionIsOk = true
    }

    internal override fun bindLayout(): Int {
        return R.layout.activity_main
    }

    internal override fun initData() {
//        initView()
        permissionTools = PermissionTools.getInstance(this, permissionlist, this)
        permissionTools?.requestRunPermission(false)
        setDate()
        startService(Intent(this, StockPriceService::class.java))
    }

    private fun textss() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tv_refreshRValue?.setOnClickListener { v: View ->
            try {
                val value1 = java.lang.Double.valueOf(StringInputUtils.value(et_cost!!)) - java.lang.Double.valueOf(StringInputUtils.value(et_stopLoss!!))
                val value2 = java.lang.Double.valueOf(StringInputUtils.value(et_mostPrice!!)) - java.lang.Double.valueOf(StringInputUtils.value(et_cost!!))
                rvalue = value2 / value1
                tv_rValue?.setTextColor(if (rvalue < 3) Color.parseColor("#008B45") else Color.RED)
                tv_rValue?.text = DoubleTools.dealMaximumFractionDigits(rvalue, 2)

                val value3 = java.lang.Double.valueOf(DoubleTools.dealMaximumFractionDigits(value2 / java.lang.Double.valueOf(StringInputUtils.value(et_cost!!)) * 100, 2))
                tv_income!!.text = DoubleTools.dealMaximumFractionDigits(value3, 2) + "%"

            } catch (ex: Exception) {
            }
        }
    }

    fun onclickSell(view: View) {
        startActivity(Intent(this@MainActivity, HistoryInfoActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        if (tv_time != null) {
            nowDate = ""
            tv_time?.text = "日期：" + TimeTools.dealTime(getNowDate())
        }
    }

    private fun setDate() {
        tv_time?.text = "日期：" + getNowDate()
    }

    private fun getNowDate(): String {
        if (TextUtils.isEmpty(nowDate)) {
            val simpleDateFormat = SimpleDateFormat("yyyyMMddHH:mm:ss")// HH:mm:ss
            //获取当前时间
            val date = Date(System.currentTimeMillis())
            nowDate = simpleDateFormat.format(date)
        }
        return nowDate
    }

    fun onclick(view: View) {
        if (isDataOk) {
            val value = if (rvalue < 3) "R比率很低哦，谨慎下单哦！" else "确定下单！"
            CustomHintDialog(this@MainActivity, { savaData() }, value, "取消", "下单", CustomHintDialog.Dialog_TYPE_1)
        }

    }

    fun onclickCompare(view: View) {
        //        if(isDataOk()){
        //            new CustomHintDialog(MainActivity.this, ()->savaDataToTempStockInfo(),"添加到比对池","取消", "添加",CustomHintDialog.Dialog_TYPE_1);
        //        }
        startActivity(Intent(this@MainActivity, TempStockInfoActivity::class.java))
    }

    fun onclickOpenFile(view: View) {
        // 判断sd卡是否存在
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            val dirName = Environment.getExternalStorageDirectory().absolutePath + File.separator + "finance"
            val file = File(dirName)
            if (null == file || !file.exists()) {
                return
            }
            val intent = Intent(Intent.ACTION_VIEW)
            //            intent.addCategory(Intent.CATEGORY_OPENABLE);
            val contentUri = FileProvider.getUriForFile(this@MainActivity, "com.yuan.lifefinance.tool.fileprovider", file)
            intent.setDataAndType(contentUri, "*/*")
            startActivity(intent)
        }
    }

    //下单保存
    fun savaData() {
        try {
            //保存数据库信息
            val stokeName = StringInputUtils.value(et_name!!)
            val cost = StringInputUtils.value(et_cost!!)
            val stopLoss = java.lang.Double.valueOf(StringInputUtils.value(et_stopLoss!!))
            val mostPrice = java.lang.Double.valueOf(StringInputUtils.value(et_mostPrice!!))
            val rValue = java.lang.Double.valueOf(StringInputUtils.value(tv_rValue!!))
            val result = DBManager.getInstance().savaStockInfo(stokeName, StringInputUtils.value(et_code!!), cost, stopLoss, mostPrice, rValue, nowDate)
            LogUtil.d("savaStockInfo", "result:$result")
            //截图保存
            val nowdate = getNowDate().replace(" ", "")
            FileTools.saveToSD(ActivityUtils.activityShot(this@MainActivity), et_name!!.text.toString() + "_" + nowdate)
            Toast.makeText(this@MainActivity, "保存成功", Toast.LENGTH_SHORT).show()

            clearData()
        } catch (ex: Exception) {
            Toast.makeText(this@MainActivity, ex.toString(), Toast.LENGTH_SHORT).show()
        }
    }


    private fun clearData() {
        et_name?.setText("")
        et_code?.setText("")
        et_cost?.setText("")
        et_stopLoss?.setText("")
        et_mostPrice?.setText("")
        tv_rValue?.text = ""
        nowDate = ""
        nowDate = getNowDate()
        tv_time?.text = "日期：" + TimeTools.dealTime(getNowDate())
    }
}
