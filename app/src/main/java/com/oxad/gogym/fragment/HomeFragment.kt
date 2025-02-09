package com.oxad.gogym.fragment

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.oxad.gogym.R
import com.oxad.gogym.activity.ComplaintForm
import com.oxad.gogym.activity.GoalTracker
import com.oxad.gogym.activity.MainActivity
import com.oxad.gogym.activity.OfferPage
import com.oxad.gogym.activity.OurProducts
import com.oxad.gogym.activity.Trainer
import com.oxad.gogym.databinding.FragmentHomeBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), View.OnClickListener  {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initUi()
        return binding.root

    }
private fun initUi(){
setclicklistener()
    // Set new gradient colors

}
    private fun setclicklistener(){
        binding.mytrainerRl.setOnClickListener(this)
        binding.ourproductsRl.setOnClickListener(this)
        binding.myoffersRl.setOnClickListener(this)
        binding.complaintRl.setOnClickListener(this)
        binding.goaltrackRl.setOnClickListener(this)

    }
    override fun onClick(p0: View?) {
        var code = 1
        when(p0) {
            binding.goaltrackRl -> code = 1
            binding.mytrainerRl -> code = 2
            binding.ourproductsRl -> code = 3
            binding.myoffersRl -> code = 4
            binding.complaintRl -> code = 5


        }
        openActivity(code)
    }
    lateinit var intentOpen:Intent

    private fun openActivity(code:Int){

        when(code)
        {
            1 -> intentOpen = Intent(requireActivity(), GoalTracker::class.java)
            2-> intentOpen = Intent(requireActivity(), Trainer::class.java)
            3-> intentOpen = Intent(requireActivity(), OurProducts::class.java)
            4-> intentOpen = Intent(requireActivity(), OfferPage::class.java)
            5-> intentOpen = Intent(requireActivity(), ComplaintForm::class.java)

        }
        if (this::intentOpen.isInitialized) {
            startActivity(intentOpen)
        }

    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}