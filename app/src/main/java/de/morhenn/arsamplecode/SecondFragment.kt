package de.morhenn.arsamplecode

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.ar.core.Config
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.Renderable
import de.morhenn.arsamplecode.databinding.FragmentSecondBinding
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArNode
import io.github.sceneview.ar.scene.PlaneRenderer
import io.github.sceneview.model.await

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private var cubeModel: Renderable? = null

    lateinit var sceneView: ArSceneView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {
            cubeModel = ModelRenderable.builder()
                .setSource(context, Uri.parse("models/cube.glb"))
                .setIsFilamentGltf(true)
                .await()
        }
        sceneView = binding.sceneView
        sceneView.configureSession { config ->
            config.cloudAnchorMode = Config.CloudAnchorMode.ENABLED
            config.planeFindingMode = Config.PlaneFindingMode.HORIZONTAL_AND_VERTICAL
            config.updateMode = Config.UpdateMode.LATEST_CAMERA_IMAGE
        }
        sceneView.planeRenderer.planeRendererMode = PlaneRenderer.PlaneRendererMode.RENDER_ALL

        sceneView.onTouchAr = { hitResult, _ ->
            val node = ArNode()
            node.anchor = hitResult.createAnchor()
            node.setRenderable(cubeModel)
            sceneView.addChild(node)

            sceneView.session?.resolveCloudAnchor("CLOUD_ANCHOR_ID_HERE")
        }
    }

    override fun onPause() {
        sceneView.onPause(this)
        super.onPause()
    }

    override fun onResume() {
        sceneView.onResume(this)
        super.onResume()
    }

    override fun onStop() {
        sceneView.onStop(this)
        super.onStop()
    }

    override fun onStart() {
        sceneView.onStart(this)
        super.onStart()
    }

    override fun onDestroy() {
        sceneView.onDestroy(this)
        super.onDestroy()
    }
}