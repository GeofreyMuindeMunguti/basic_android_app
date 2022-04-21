package com.example.helloworld.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class SyncWorker(context: Context,
                 workerParams: WorkerParameters
): Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.d("WORKER", "Successfully run a worker task.")
        return Result.success()
    }
}