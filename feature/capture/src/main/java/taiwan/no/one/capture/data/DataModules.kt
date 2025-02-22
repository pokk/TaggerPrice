/*
 * MIT License
 *
 * Copyright (c) 2019 SmashKs
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package taiwan.no.one.capture.data

import android.content.Context
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import taiwan.no.one.capture.FeatModules.Companion.FEAT_NAME
import taiwan.no.one.capture.data.local.config.CaptureDatabase
import taiwan.no.one.capture.data.local.service.database.v1.CaptureDao
import taiwan.no.one.capture.data.local.service.json.v1.CaptureFile
import taiwan.no.one.capture.data.repository.CaptureRepository
import taiwan.no.one.capture.data.store.LocalStore
import taiwan.no.one.capture.data.store.RemoteStore
import taiwan.no.one.capture.domain.repository.CaptureRepo
import taiwan.no.one.taggerprice.provider.ModuleProvider

internal object DataModules : ModuleProvider {
    override fun provide(context: Context) = DI.Module("${FEAT_NAME}DataModule") {
        import(localProvide())
        import(remoteProvide())

        bind<LocalStore>() with singleton { LocalStore(instance(), instance()) }
        bind<RemoteStore>() with singleton { RemoteStore() }

        bind<CaptureRepo>() with singleton { CaptureRepository(instance(), instance()) }
    }

    private fun localProvide() = DI.Module("${FEAT_NAME}LocalModule") {
        bind<CaptureDatabase>() with singleton { CaptureDatabase.getDatabase(instance()) }

        bind<CaptureFile>() with singleton { CaptureFile(instance()) }
        bind<CaptureDao>() with singleton { instance<CaptureDatabase>().createCaptureDao() }
    }

    private fun remoteProvide() = DI.Module("${FEAT_NAME}RemoteModule") {
    }
}
