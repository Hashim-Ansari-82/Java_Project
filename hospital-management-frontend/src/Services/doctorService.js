// src/services/doctorService.js

import api from "./api";

// GET all doctors
export const getDoctors = () =>
    api.get("/doctors");

// GET doctor by ID
export const getDoctorById = (id) =>
    api.get(`/doctors/${id}`);

// CREATE doctor
export const createDoctor = (doctor) =>
    api.post("/doctors", doctor);

// UPDATE doctor
export const updateDoctor = (id, doctor) =>
    api.put(`/doctors/${id}`, doctor);

// DELETE doctor
export const deleteDoctor = (id) =>
    api.delete(`/doctors/${id}`);