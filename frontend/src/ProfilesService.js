import axios from 'axios';
const API_URL = 'http://localhost:8000';

export default class CustomersService{

    constructor(){}

    getProfiles() {
        const url = `${API_URL}/api/profiles/`;
        return axios.get(url).then(response => response.data);
    }
    getProfileByURL(link){
        const url = `${API_URL}${link}`;
        return axios.get(url).then(response => response.data);
    }
    getProfile(pk) {
        const url = `${API_URL}/api/profiles/${pk}`;
        return axios.get(url).then(response => response.data);
    }
    deleteProfile(profile){
        const url = `${API_URL}/api/profiles/${profile.pk}`;
        return axios.delete(url);
    }
    createProfile(customer){
        const url = `${API_URL}/api/profiles/`;
        return axios.post(url,customer);
    }
    updateProfile(profile){
        const url = `${API_URL}/api/profiles/${profile.pk}`;
        return axios.put(url,profile);
    }
}