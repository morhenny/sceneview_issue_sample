package de.morhenn.arsamplecode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.ar.core.Config
import de.morhenn.arsamplecode.databinding.FragmentSecondBinding
import io.github.sceneview.ar.ArSceneView

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    lateinit var sceneView: ArSceneView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sceneView = binding.sceneView
        sceneView.configureSession { config ->
            config.cloudAnchorMode = Config.CloudAnchorMode.ENABLED
            config.updateMode = Config.UpdateMode.LATEST_CAMERA_IMAGE
        }
    }
}