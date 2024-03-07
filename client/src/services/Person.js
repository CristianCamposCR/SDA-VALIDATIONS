import AxiosInstance from '../kernel/AxiosConfig'
const SERVER_URL = import.meta.env.VITE_APP_BASE_URL;
const savePerson = async (payload) => {
  try {
    const response = await AxiosInstance.post(`/person/`, payload);
    return response.data;
  } catch (error) {
    return error.response.data
  }
};

export default {
  savePerson,
};
