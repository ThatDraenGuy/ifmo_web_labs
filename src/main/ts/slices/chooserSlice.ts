import {createSlice, PayloadAction} from "@reduxjs/toolkit";


export interface ChooserState {
    x: number,
    y: number,
    r: number
}
const initialState: ChooserState = {
    x: undefined,
    y: undefined,
    r: 3
}

export const chooserSlice = createSlice({
    name: 'chooser',
    initialState,
    reducers: {
        setR: ((state, action: PayloadAction<number>) => {
            state.r = action.payload
        })
    }
})

export const {setR} = chooserSlice.actions

export default  chooserSlice.reducer