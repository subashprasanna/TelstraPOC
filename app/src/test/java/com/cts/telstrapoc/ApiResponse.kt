package com.cts.telstrapoc

import com.cts.telstrapoc.model.CanadaAPIDetail
import com.cts.telstrapoc.model.CanadaAPIDetailInfo

class ApiResponse {
    companion object {
        fun getSampleResponse() : CanadaAPIDetail {
            val title = "About Canada"
            val list : MutableList<CanadaAPIDetailInfo> = mutableListOf()
            list.add(CanadaAPIDetailInfo(description = "", imageHref = "", title = "Beavers"))
            list.add(CanadaAPIDetailInfo("", "", "Flag"))
            list.add(CanadaAPIDetailInfo("", "", "Transportation"))
            list.add(CanadaAPIDetailInfo("", "", "Hockey Night in Canada"))
            list.add(CanadaAPIDetailInfo("", "", "Eh"))
            list.add(CanadaAPIDetailInfo("", "", "Housing"))
            list.add(CanadaAPIDetailInfo("", "", "Public Shame"))
            list.add(CanadaAPIDetailInfo("", "", ""))
            list.add(CanadaAPIDetailInfo("", "", "Space Program"))
            list.add(CanadaAPIDetailInfo("", "", "Meese"))
            list.add(CanadaAPIDetailInfo("", "", "Geography"))
            list.add(CanadaAPIDetailInfo("", "", "Kittens"))
            list.add(CanadaAPIDetailInfo("", "", "Mounties"))
            list.add(CanadaAPIDetailInfo("", "", "Language"))
            return CanadaAPIDetail(list, title)
        }
    }
}