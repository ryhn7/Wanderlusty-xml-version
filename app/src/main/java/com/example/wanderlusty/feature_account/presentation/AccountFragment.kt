package com.example.wanderlusty.feature_account.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.wanderlusty.R
import com.example.wanderlusty.databinding.FragmentAccountBinding
import com.example.wanderlusty.feature_explore_tourism.presentation.explore.AddTourismActivity

class AccountFragment : Fragment() {
    private var _binding: FragmentAccountBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAccountBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configScreen()
    }

    private fun configScreen() {
//        Section Preferences
        val textLanguage = binding.itemLanguage.tvSettingOption
        val textCurrency = binding.itemCurrency.tvSettingOption
        val textUnits = binding.itemUnits.tvSettingOption
        val textNotification = binding.itemNotification.tvSettingOption

        textLanguage.text = getString(R.string.language)
        textCurrency.text = getString(R.string.currency)
        textUnits.text = getString(R.string.units)
        textNotification.text = getString(R.string.notification)

        val lastDivider = binding.itemNotification.divider
        lastDivider.visibility = View.GONE

        val valueTextLanguage = binding.itemLanguage.tvValueOption
        val valueTextCurrency = binding.itemCurrency.tvValueOption
        val valueTextUnits = binding.itemUnits.tvValueOption
        val valueTextNotification = binding.itemNotification.tvValueOption

        valueTextLanguage.text = getString(R.string.lang_value)
        valueTextCurrency.text = getString(R.string.currency_value)
        valueTextUnits.text = getString(R.string.units_value)
        valueTextNotification.text = ""
//        End Section Preferences

//        Section Support
        val textHelp = binding.itemHelpCenter.tvSettingOption
        val textAppFeedback = binding.itemAppFeedback.tvSettingOption
        val textPolicy = binding.itemPolicy.tvSettingOption

        textHelp.text = getString(R.string.help_center)
        textAppFeedback.text = getString(R.string.app_feedback)
        textPolicy.text = getString(R.string.privacy_policy)

        val lastDividerSupport = binding.itemPolicy.divider
        lastDividerSupport.visibility = View.GONE
//        End Section Support
    }

}