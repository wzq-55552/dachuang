import axios from 'axios'

export function request(config) {
  const instance = axios.create({
    // baseURL: 'http://152.136.185.210:8000/api/h8',
    baseURL: 'http://47.113.80.250:9001',
    timeout: 5000
  })
  instance.interceptors.request.use(config => {
    // console.log(config);
    // console.log('--------------');
    // console.log(config.data);
    if (localStorage.getItem('Authorization')) {
      config.headers.Authorization = localStorage.getItem('Authorization');
    }
    // console.log(config.headers.Authorization);
    // console.log(config.headers);
    return config;
  }, err => {
    return Promise.reject(err);
  })
  instance.interceptors.response.use(res => {
    // console.log(res);
    return res.data;
  }, err => {
    // console.log(err);
  })
  return instance(config);
}

export function request3(config) {
  const instance = axios.create({
    baseURL: 'http://47.113.80.250:9003',
    timeout: 5000
  })
  instance.interceptors.request.use(config => {
    if (localStorage.getItem('Authorization')) {
      config.headers.Authorization = localStorage.getItem('Authorization');
    }
    return config;
  }, err => {
    return Promise.reject(err);
  })
  instance.interceptors.response.use(res => {
    return res.data;
  }, err => {
  })
  return instance(config);
}
