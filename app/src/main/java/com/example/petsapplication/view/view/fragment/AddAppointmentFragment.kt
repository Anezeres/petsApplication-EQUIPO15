package com.example.petsapplication.view.view.fragment

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.petsapplication.R
import com.example.petsapplication.databinding.FragmentAddAppointmentBinding
import com.example.petsapplication.view.model.Appointment
import com.example.petsapplication.view.viewmodel.AppointmentViewModel


class AddAppointmentFragment : Fragment() {

    private lateinit var binding: FragmentAddAppointmentBinding
    private val appointmentViewModel: AppointmentViewModel by viewModels()
    private lateinit var imageUrl: String

    private val symptomsList = listOf(
        "Síntomas",
        "Solo duerme",
        "No come",
        "Fractura extremidad",
        "Tiene pulgas",
        "Tiene garrapatas",
        "Bota demasiado pelo"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddAppointmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupListeners()
        observerViewModel()
    }

    private fun setupViews() {
        // Configurar el Spinner con los síntomas
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, symptomsList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerSymptoms.adapter = adapter
    }

    private fun setupListeners()
    {
        binding.toolbar.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.spinnerSymptoms.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedSymptom = symptomsList[position]
                    val isSymptomSelected = selectedSymptom != "Síntomas"

                    binding.btnSaveAppointment.setOnClickListener {
                        if (!isSymptomSelected) {
                            Toast.makeText(
                                requireContext(),
                                "Selecciona un síntoma",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            saveAppointment()
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Toast.makeText(requireContext(), "Selecciona un síntoma", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        // Validar datos para habilitar el botón de guardar
        binding.etPetName.addTextChangedListener(watcher)
        binding.etPetBreed.addTextChangedListener(watcher)
        binding.etOwnerName.addTextChangedListener(watcher)
        binding.etPhone.addTextChangedListener(watcher)
    }

    private val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            val isAllFieldsNotEmpty = listOf(
                binding.etPetName.text,
                binding.etPetBreed.text,
                binding.etOwnerName.text,
                binding.etPhone.text
            ).all { it?.isNotEmpty() ?: false }  // Handle nullable Editable safely

            binding.btnSaveAppointment.isEnabled = isAllFieldsNotEmpty
            binding.btnSaveAppointment.setTextColor(
                if (isAllFieldsNotEmpty) Color.WHITE else ContextCompat.getColor(
                    requireContext(),
                    R.color.gray
                )
            )
        }
    }

    private fun observerViewModel() {
        observerBreeds()
        observerImageURL()
    }

    private fun saveAppointment() {

        val name = binding.etPetName.text.toString()
        val breed = binding.etPetBreed.text.toString()
        val owner = binding.etOwnerName.text.toString()
        val phone = binding.etPhone.text.toString()
        val symptoms = binding.spinnerSymptoms.selectedItem.toString()
        val imageUrl = imageUrl

        val appointment = Appointment(
            petName = name,
            petBreed = breed,
            ownerName = owner,
            ownerPhone = phone,
            symptoms = symptoms,
            photoUrl = imageUrl
        )

        appointmentViewModel.saveAppointment(appointment)
        Toast.makeText(requireContext(), "Cita agendada!!!", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }

    /*
        private fun observerViewModel(){
            observerImageURL()
        }
    */

    private fun observerBreeds() {
        appointmentViewModel.getBreeds()
        appointmentViewModel.breedList.observe(viewLifecycleOwner) { breeds ->
            val breedsList: MutableList<String> = mutableListOf()

            for ((breed, variants) in breeds) {
                if (variants.isEmpty()) {
                    breedsList.add(breed.replaceFirstChar { it.titlecase() })
                } else {
                    for (variant in variants) {
                        breedsList.add("${breed.replaceFirstChar { it.titlecase() }} ${variant.replaceFirstChar { it.titlecase() }}")
                    }
                }
            }

            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, breedsList)
            binding.etPetBreed.setAdapter(adapter)
        }
    }

    private fun observerImageURL() {
        appointmentViewModel.getImageURL()
        appointmentViewModel.imageURL.observe(viewLifecycleOwner) { image ->
            imageUrl = image.url
        }
    }
}