import { configureStore, combineReducers } from '@reduxjs/toolkit';
import bookSlice from '../state/bookSlice';
import loginSlice from '../state/loginSlice';


const rootReducer = combineReducers({
    books: bookSlice,
    login: loginSlice
})

export const store = configureStore({
    reducer: rootReducer
});