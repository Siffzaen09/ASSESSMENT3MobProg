package org.d3if4081.ass1.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if4081.ass1.R
import org.d3if4081.ass1.databinding.FragmentHitungBinding
import org.d3if4081.ass1.db.JajargenjangDb
import org.d3if4081.ass1.model.Hasil

class HitungFragment : Fragment() {

    private lateinit var binding: FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        val db = JajargenjangDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        binding.buttonreset.setOnClickListener { reset() }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonhitungjg.setOnClickListener { hitungJg() }
        binding.shareButton.setOnClickListener { shareData() }
        viewModel.getHasil().observe(requireActivity(), { showResult(it) })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(
                R.id.action_hitungFragment_to_historiFragment)
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_aboutFragment
                )
                return true
            }
            R.id.menu_benda_jajar_genjang -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_bendaJajarGenjangFragment
                )
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun hitungJg() {

          val alas = binding.alasEditText.text.toString()
          if (TextUtils.isEmpty(alas)) {
              Toast.makeText(context, R.string.alas_invalid, Toast.LENGTH_LONG).show()
              return
          }
          val tinggi = binding.tinggiEditText.text.toString()
          if (TextUtils.isEmpty(tinggi)) {
              Toast.makeText(context, R.string.tinggi_invalid, Toast.LENGTH_LONG).show()
              return
          }
         viewModel.hitung(
         alas.toFloat(),
         tinggi.toFloat()
        )
    }

    private fun reset(){
        binding.alasEditText.setText("")
        binding.tinggiEditText.setText("")
        binding.luasTextView.setText("")
    }


    private fun showResult(result: Hasil?) {
        if (result == null) return
        binding.luasTextView.text = getString(R.string.luas_x, result.luasJajarGenjang)
    }

    private fun shareData() {
        val message = getString(R.string.bagikan_template,
            binding.alasEditText.text,
            binding.tinggiEditText.text,
            binding.luasTextView.text
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }
}