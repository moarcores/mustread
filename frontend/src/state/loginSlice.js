import { createSlice } from '@reduxjs/toolkit';

const loginSlice = createSlice({
    name: 'login',
    initialState: {
        token: null,
    },
    reducers: {
        setToken(state, action) {
            state.token = action.payload;
        }
    }
})

export const {
    setToken,
} = loginSlice.actions;

export default loginSlice.reducer;
