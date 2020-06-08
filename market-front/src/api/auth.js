import axios from 'axios'

const BASE_URL = "http://localhost:8080"

const client = axios.create()

export const loginByGoogle = code => client.post(`${BASE_URL}/auth/google`, { code })