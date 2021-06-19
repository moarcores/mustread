import {createSlice} from '@reduxjs/toolkit';

const booksSlice = createSlice({
    name: 'books',
    initialState: {
        books: {},
        selectedBook: '',
        favoriteBooks: [],
    },
    reducers: {

    }
})

export default booksSlice.reducer;
