package ie.wit.madassignment.ui.alarms

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import ie.wit.madassignment.R
import ie.wit.madassignment.databinding.FragmentAlarmBinding

class AlarmsFragment : Fragment() {

    private val TOPIC = "alarm"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentAlarmBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_alarm, container, false
        )

        val viewModel = ViewModelProviders.of(this).get(AlarmsViewModel::class.java)

        binding.alarmsViewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        createChannel(
            getString(R.string.alarm_notification_channel_id),
            getString(R.string.alarm_notification_channel_name)
        )

        return binding.root
    }

    private fun createChannel(channelId: String, channelName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,

                NotificationManager.IMPORTANCE_HIGH
            )
                .apply {
                    setShowBadge(false)
                }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description =
                getString(R.string.timer_notification_channel_description)

            val notificationManager = requireActivity().getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    companion object {
        fun newInstance() = AlarmsFragment()
    }
}
