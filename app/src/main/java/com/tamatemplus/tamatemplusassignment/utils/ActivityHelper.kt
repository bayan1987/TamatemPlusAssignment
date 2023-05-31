package com.tamatemplus.tamatemassignment

import android.app.Activity
import android.content.Context

import android.content.Intent

import android.os.Bundle




object ActivityHelper {

    fun goToActivity(ctx: Context, to: Class<*>?, isFinished: Boolean) {
        val i = Intent(ctx, to)
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        //        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        ctx.startActivity(i)
        //        ((Activity)ctx).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        if (isFinished) (ctx as Activity).finish()
    }

    fun goToActivityWithNewTask(ctx: Context, to: Class<*>?) {
        val i = Intent(ctx, to)
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        ctx.startActivity(i)
    }

    fun goToActivity(ctx: Context, to: Class<*>?, isFinished: Boolean, bundle: Bundle) {
        val i = Intent(ctx, to)
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        i.putExtras(bundle)
        ctx.startActivity(i)
        if (isFinished) (ctx as Activity).finish()
    }

    fun startActivityForResult(ctx: Context, to: Class<*>?, id: Int, bundle: Bundle?) {
        val i = Intent(ctx, to)
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        if (bundle != null) i.putExtras(bundle)
        (ctx as Activity).startActivityForResult(i, id)
    }

    fun goToActivityWithClearTop(ctx: Context, to: Class<*>?, isFinished: Boolean) {
        val i = Intent(ctx, to)
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

//        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        ctx.startActivity(i)
        if (isFinished) (ctx as Activity).finish()
    }

    fun goToActivityWithClearTop(
        ctx: Context,
        to: Class<*>?,
        isFinished: Boolean,
        bundle: Bundle?
    ) {
        val i = Intent(ctx, to)
        //        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        i.putExtras(bundle!!)
        ctx.startActivity(i)
        if (isFinished) (ctx as Activity).finish()
    }
}