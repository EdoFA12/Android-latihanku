package com.edofa.kotlinkalkulator

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.edofa.kotlinkalkulator.databinding.FragmentDialongBuilderBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DialogFragmentBuilder : DialogFragment() {
private var _binding : FragmentDialongBuilderBinding ? = null
    private val binding get() = _binding!!



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentDialongBuilderBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .setPositiveButton("Skip Login"){_,_ ->
            }
            .setNegativeButton("Login"){_,_ ->
            }
        return builder.show()
    }



//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//    }



    companion object {
        const val TAG = "PurchaseConfirmationDialog"
    }


}


