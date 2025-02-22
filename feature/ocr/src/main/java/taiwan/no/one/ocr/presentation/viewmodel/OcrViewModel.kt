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

package taiwan.no.one.ocr.presentation.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import taiwan.no.one.core.presentation.viewmodel.BehindViewModel
import taiwan.no.one.core.presentation.viewmodel.ResultLiveData
import taiwan.no.one.ktx.livedata.toLiveData
import taiwan.no.one.ocr.domain.parameter.OcrRequestParams
import taiwan.no.one.ocr.domain.usecase.FetchRecognizeCase

class OcrViewModel(
    private val fetchRecognizeCase: FetchRecognizeCase
) : BehindViewModel() {
    private val _result by lazy { ResultLiveData<String>() }
    val ocrResult = _result.toLiveData()

    fun getOcrResult(bitmap: Bitmap) = viewModelScope.launch {
        _result.value = fetchRecognizeCase.execute(OcrRequestParams(bitmap))
    }
}
