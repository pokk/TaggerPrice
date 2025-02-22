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

package taiwan.no.one.taggerprice.di

import android.content.Context
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import taiwan.no.one.taggerprice.provider.CurrencyMethodProvider
import taiwan.no.one.taggerprice.provider.ModuleProvider
import taiwan.no.one.taggerprice.provider.OCRMethodProvider
import java.util.ServiceLoader

object FeatModuleHelper {
    fun kodeinModules(context: Context) = ServiceLoader.load(ModuleProvider::class.java).map { it.provide(context) }

    fun provide() = DI.Module("Feature Method Provider Module") {
        bind<CurrencyMethodProvider>() with singleton {
            ServiceLoader.load(CurrencyMethodProvider::class.java).toList().first()
        }
        bind<OCRMethodProvider>() with singleton {
            ServiceLoader.load(OCRMethodProvider::class.java).toList().first()
        }
    }
}

