package com.example.petsapplication.view.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.petsapplication.R
import com.example.petsapplication.databinding.FragmentEditAppointmentBinding
import com.example.petsapplication.view.api.DogBreedsResponse
import com.example.petsapplication.view.api.RetrofitClient
import com.example.petsapplication.view.model.InventoryAppointment
import com.example.petsapplication.view.viewmodel.AppointmentModel
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditAppointmentFragment : Fragment() {
    private lateinit var binding: FragmentEditAppointmentBinding
    private val appointmentModel: AppointmentModel by viewModels()

    // NUEVAS VARIABLES para autocomplete
    private lateinit var adapter: ArrayAdapter<String>
    private val breedList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditAppointmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recupera los datos del bundle
        val petName = arguments?.getString("petName")
        val petBreed = arguments?.getString("petBreed")
        val petDetails = arguments?.getString("petDetails")
        val owner = arguments?.getString("owner")
        val phone = arguments?.getString("phone")
        val idNumber = arguments?.getString("idNumber")

        // Asigna los datos a los campos
        binding.editPetName.setText(petName)
        binding.editOwner.setText(owner)
        binding.editPhone.setText(phone)
        binding.autoCompleteText.setText(petBreed)
        binding.idNumber.text = idNumber

        if (idNumber != null && petDetails != null) {
            controller(idNumber.toInt(), petDetails, view)
        }

        // Configura autocomplete y trae razas
        setupAutoCompleteTextView()
        fetchDogBreeds()

        // VALIDACIÓN DE CAMPOS
        val petNameEditText = binding.editPetName
        val ownerEditText = binding.editOwner
        val breedAutoComplete = binding.autoCompleteText
        val phoneEditText = binding.editPhone
        val btnEdit = binding.btnEdit

        fun validateFields() {
            val isPetNameFilled = !petNameEditText.text.isNullOrBlank()
            val isOwnerFilled = !ownerEditText.text.isNullOrBlank()
            val isBreedFilled = !breedAutoComplete.text.isNullOrBlank()
            val isPhoneFilled = !phoneEditText.text.isNullOrBlank()

            val isEnabled = isPetNameFilled && isOwnerFilled && isBreedFilled && isPhoneFilled

            btnEdit.isEnabled = isEnabled
            btnEdit.alpha = if (isEnabled) 1.0f else 0.5f
        }

        petNameEditText.addTextChangedListener { validateFields() }
        ownerEditText.addTextChangedListener { validateFields() }
        breedAutoComplete.addTextChangedListener { validateFields() }
        phoneEditText.addTextChangedListener { validateFields() }
    }

    private fun setupAutoCompleteTextView() {
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, breedList)
        binding.autoCompleteText.setAdapter(adapter)
        binding.autoCompleteText.threshold = 2
    }

    private fun fetchDogBreeds() {
        RetrofitClient.dogApiService.dogBreeds?.enqueue(object : Callback<DogBreedsResponse?> {
            override fun onResponse(call: Call<DogBreedsResponse?>, response: Response<DogBreedsResponse?>) {
                if (response.isSuccessful) {
                    response.body()?.message?.let { breeds ->
                        breedList.clear()
                        for ((key, value) in breeds) {
                            breedList.add(key)
                            value.forEach { subBreed ->
                                breedList.add("$key $subBreed")
                            }
                        }
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(requireContext(), "Failed to fetch dog breeds", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DogBreedsResponse?>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    @SuppressLint("CutPasteId")
    fun controller(id: Int, details: String, view: View) {
        val editPetNameOld: TextInputEditText = view.findViewById(R.id.editPetName)
        val editOwnerOld: TextInputEditText = view.findViewById(R.id.editOwner)
        val editPhoneOld: TextInputEditText = view.findViewById(R.id.editPhone)
        val autoCompleteTextViewOld: AutoCompleteTextView = view.findViewById(R.id.autoCompleteText)

        val petNameTextOld: String = editPetNameOld.text.toString()
        val ownerTextOld: String = editOwnerOld.text.toString()
        val phoneTextOld: String = editPhoneOld.text.toString()
        val petBreedTextOld: String = autoCompleteTextViewOld.text.toString()

        binding.backButton.setOnClickListener {
            val bundle = Bundle().apply {
                putString("petName", petNameTextOld)
                putString("petBreed", petBreedTextOld)
                putString("petDetails", details)
                putString("owner", ownerTextOld)
                putString("phone", phoneTextOld)
                putString("idNumber", id.toString())
            }
            findNavController().navigate(R.id.action_editAppointmentFragment_to_appointmentDetailsFragment, bundle)
        }

        binding.btnEdit.setOnClickListener {
            val petNameText: String = editPetNameOld.text.toString()
            val ownerText: String = editOwnerOld.text.toString()
            val phoneText: String = editPhoneOld.text.toString()
            val petBreedText: String = autoCompleteTextViewOld.text.toString()

            val bundle = Bundle().apply {
                putString("petName", petNameText)
                putString("petBreed", petBreedText)
                putString("petDetails", details)
                putString("owner", ownerText)
                putString("phone", phoneText)
                putString("idNumber", id.toString())
            }

            val inventory = InventoryAppointment(
                id = id,
                pet_name = petNameText,
                pel_symptoms = details,
                tel_numbe = phoneText,
                owner_name = ownerText,
                pet_breed = petBreedText,
            )
            appointmentModel.update(inventory)
            findNavController().navigate(R.id.action_editAppointmentFragment_to_appointmentDetailsFragment, bundle)
        }
    }

    fun saveChanges() {}
    fun selectdata() {}
}
