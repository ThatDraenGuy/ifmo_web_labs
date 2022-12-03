import {createSlice, PayloadAction} from "@reduxjs/toolkit";


export interface PaginationState {
    currentPage: number,
    itemsPerPage: number
}

const initialState: PaginationState = {
    currentPage: 1,
    itemsPerPage: 5
}

export const paginationSlice = createSlice({
    name: 'pagination',
    initialState,
    reducers: {
        setCurrentPage: ((state, action:PayloadAction<number>) => {
            state.currentPage = action.payload;
        }),
        setItemsPerPage: ((state, action:PayloadAction<number>) => {
            state.itemsPerPage = action.payload;
        })
    }
})


export const {setCurrentPage, setItemsPerPage} = paginationSlice.actions;

export default paginationSlice.reducer