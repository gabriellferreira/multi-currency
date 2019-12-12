package br.com.gabriellferreira.multicurrency.presentation.view.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.gabriellferreira.multicurrency.presentation.di.AppApplication
import br.com.gabriellferreira.multicurrency.presentation.di.ControllerComponent
import br.com.gabriellferreira.multicurrency.presentation.di.ControllerModule
import br.com.gabriellferreira.multicurrency.presentation.view.BaseContract
import br.com.gabriellferreira.multicurrency.presentation.view.activity.BaseActivity

@Suppress("unused")
abstract class BaseFragment<T, in V> : Fragment(),
        BaseContract.View where T : BaseContract.Presenter<V>, V : BaseContract.View {

    private var mControllerComponent: ControllerComponent? = null
    protected var presenter: T? = null
        set(value) {
            if (field == null) {
                field = value
            }
        }

    protected abstract fun createPresenter(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        safeAttachView()
    }

    @Suppress("UNCHECKED_CAST", "TooGenericExceptionThrown")
    private fun safeAttachView() {
        try {
            presenter?.attachView(this as V)
        } catch (e: ClassCastException) {
            throw RuntimeException("Type V must be the same type of this class", e)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
        presenter?.detachView()
        presenter = null
    }

    protected fun getControllerComponent(): ControllerComponent {
        if (mControllerComponent == null) {
            mControllerComponent = AppApplication.applicationComponent
                    .newControllerComponent(ControllerModule(activity as AppCompatActivity))
        }
        return mControllerComponent!!
    }

    override fun showToast(msg: String) {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).showToast(msg)
        }
    }
}