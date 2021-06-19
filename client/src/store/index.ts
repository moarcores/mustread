import {configureStore, combineReducers} from '@reduxjs/toolkit';
import bookSlice from '../state/bookSlice';

const rootReducer = combineReducers({
    books: bookSlice
})

export const store = configureStore({
    reducer: rootReducer
})
