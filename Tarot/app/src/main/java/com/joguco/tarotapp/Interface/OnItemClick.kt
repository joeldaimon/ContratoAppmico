package com.joguco.tarotapp.Interface

import com.joguco.tarotapp.Model.Carta

/*
* Interface OnItemClick
 */
interface OnItemClick {
    //Function that initiates whe you click a Serie
    fun onItemClick(carta: Carta)
}