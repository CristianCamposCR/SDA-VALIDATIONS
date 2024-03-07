import axios from "axios";


const SERVER_URL = import.meta.env.VITE_APP_BASE_URL;

const instance = axios.create({
    baseURL:SERVER_URL,
    timeout:30_000
});

export default instance;