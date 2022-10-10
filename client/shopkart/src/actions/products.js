import {
    GET_PRODUCTS,
    CREATE_PRODUCTS,
    UPDATE_PRODUCTS,
    DELETE_PRODUCTS
  } from "./types";
  
  import axios from "axios";
  
  export const getProducts = () => dispatch => {
    console.log(localStorage.getItem("token"));
    return axios
      .get("http://localhost:7000/api/products/all", {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
      })
      .then(res => {
        dispatch({
          type: GET_PRODUCTS,
          payload: res.data.data
        });
        console.log(res.data);
      })
      .catch(err => {
        console.log(err);
      });
  };
  
  export const createProducts = PRODUCTS => dispatch => {
    axios
      .post("http://localhost:8080/admin/item", PRODUCTS,
    //    {
    //     headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
    //   }
      )
      .then(res => {
        dispatch({
          type: CREATE_PRODUCTS
        });
        alert("Added one product successfully");
        // dispatch(getProducts());
      })
      .catch(err => {
        console.log(err);
        alert("Try Again");
      });
  };
  
  export const updateProducts = products => dispatch => {
    axios
      .put(
        "http://localhost:7000/api/products/update/" + products._id,
        products,
        {
          headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
        }
      )
      .then(res => {
        dispatch({
          type: UPDATE_PRODUCTS
        });
        dispatch(getProducts());
        alert("Updated Successfully");
      })
      .catch(err => {
        console.log(err);
      });
  };
  
  export const deleteProducts = id => dispatch => {
    axios
      .delete("http://localhost:7000/api/products/delete/" + id, {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
      })
      .then(res => {
        dispatch({
          type: DELETE_PRODUCTS
        });
        dispatch(getProducts());
        alert("Deleted Successfully");
      })
      .catch(err => {
        console.log(err);
      });
  };
  