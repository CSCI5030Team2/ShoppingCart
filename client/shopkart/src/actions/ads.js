import { GET_ADS } from "./types";
import axios from "axios";
export const getAds = () => dispatch => {
    //console.log(localStorage.getItem("token"));
    return axios
      .get("http://localhost:8080/ads")
      .then(res => {
        dispatch({
          type: GET_ADS,
          payload: res.data
        });
        console.log(res.data);
      })
      .catch(err => {
        console.log(err);
      });
  };
