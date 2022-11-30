import {createSelector, createSlice, PayloadAction} from "@reduxjs/toolkit";


export interface ChooserState {
    x: number,
    y: number,
    r: number
}
const initialState: ChooserState = {
    x: undefined,
    y: undefined,
    r: undefined
}

export const chooserSlice = createSlice({
    name: 'chooser',
    initialState,
    reducers: {
        setX: ((state, action: PayloadAction<number>) => {
            state.x = action.payload
        }),
        setY: ((state, action: PayloadAction<number>) => {
            state.y = action.payload
        }),
        setR: ((state, action: PayloadAction<number>) => {
            state.r = action.payload
        })
    }
})

export const {setX, setY, setR} = chooserSlice.actions

export default  chooserSlice.reducer