package com.globits.nimpe.ui.home.request_location

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import retrofit2.Callback
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.globits.nimpe.R
import com.globits.nimpe.data.model.DengueLocation
import com.globits.nimpe.data.model.DengueLocationFilter
import com.globits.nimpe.data.model.Page
import com.globits.nimpe.data.network.ReDengueLocationApi
import com.globits.nimpe.data.network.RemoteDataSource
import com.globits.nimpe.ui.MainActivity
import com.globits.nimpe.ui.home.HomeViewState
import com.globits.nimpe.ui.home.HomeViewmodel
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class NotificationWorker(val context: Context, workerParams: WorkerParameters?) : Worker(
    context,
    workerParams!!
) {
    override fun doWork(): Result {

        var pendingIntent: PendingIntent = Intent(
            applicationContext,
            MainActivity::class.java
        ).let { intentNotification ->
            PendingIntent.getActivity(applicationContext, 0, intentNotification, 0)
        }
        var api = RemoteDataSource().buildApi(ReDengueLocationApi::class.java, context)
            .getDengueLocation(DengueLocationFilter(1, 100))
        api.enqueue(object :Callback<Page<DengueLocation>>{
            override fun onResponse(
                call: Call<Page<DengueLocation>>,
                response: Response<Page<DengueLocation>>
            ) {
                val sdf = SimpleDateFormat("hh:mm:ss")
                val currentDate = sdf.format(Date())
                var notification = NotificationCompat.Builder(applicationContext, 55.toString())
                    .setContentTitle("Dengue Alert - lúc$currentDate")
                    .setContentText("Xin chào các bạn"+ (response.body()?.content?.size))
                    .setSmallIcon(R.drawable.ic_lancher)
                    .setContentIntent(pendingIntent)
                    .setTicker("ticker")
                with(NotificationManagerCompat.from(applicationContext)) {
                    notify(8, notification.build())
                }
            }

            override fun onFailure(call: Call<Page<DengueLocation>>, t: Throwable) {
                t.printStackTrace()
            }


        })

        return Result.retry()
    }


}

