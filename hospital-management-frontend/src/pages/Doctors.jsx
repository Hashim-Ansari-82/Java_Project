import { useState, useEffect } from "react";
import { FaPlus, FaSearch } from "react-icons/fa";
import Select from 'react-select';

import { FaUserDoctor, FaHeartPulse, FaBrain, FaBuilding } from "react-icons/fa6";
import API from "../api/axiosConfig";


function Doctors() {


    useEffect(() => {

        getDoctors();

    }, []);

    const handleSaveDoctor = async (e) => {
        e.preventDefault();


        console.log("Save button clicked");
        console.log("Doctor Data:", doctor);

        try {

            // Backend ko sirf required fields bhejo
            const payload = {
                name: doctor.name,
                specialization: doctor.specialization,
                departmentId: Number(doctor.departmentId)
            };

            console.log("Payload:", payload);

            if (isEdit) {
                console.log("Updating...");

                const response = await API.put(
                    `/doctors/${doctor.id}`,
                    payload
                );

                console.log(response.data);

            } else {

                console.log("Adding...");

                const response = await API.post(
                    "/doctors",
                    payload
                );

                console.log(response.data);
            }

            // Refresh doctor list
            await getDoctors();

            // Reset form
            setDoctor({
                name: "",
                specialization: "",
                departmentId: "",
                department: ""
            });

            setIsEdit(false);
            setShowModal(false);

        } catch (error) {

            console.error("Full Error:", error);

            if (error.response) {
                console.error("Status:", error.response.status);
                console.error("Response:", error.response.data);
            } else {
                console.error("Network Error:", error.message);
            }
        }

    };


    const handleDeleteDoctor = async (id) => {

        try {

            await API.delete(`/doctors/${id}`);


            setDoctors(
                doctors.filter(
                    (doc) => doc.id !== id
                )
            );


        }
        catch (error) {

            console.log(error);

        }

    };

    const handleEditDoctor = (doc) => {
        setDoctor({
            id: doc.id,
            name: doc.name,
            specialization: doc.specialization,
            departmentId: doc.departmentId
        });

        setIsEdit(true);
        setShowModal(true);
    };

    const [doctor, setDoctor] = useState({
        id: "",
        name: "",
        specialization: "",
        departmentId: ""
    });

    const [showModal, setShowModal] = useState(false);
    const [isEdit, setIsEdit] = useState(false);
    const [search, setSearch] = useState("");
    const [doctors, setDoctors] = useState([]);
    // States
    const [departments, setDepartments] = useState([]);

    // Convert for react-select
    const departmentOptions = departments.map((dept) => ({
        value: dept.id,
        label: dept.name
    }));

    // API
    const getDepartments = async () => {
        try {
            const response = await API.get('/departments');
            setDepartments(response.data);
        } catch (error) {
            console.log(error);
        }
    };

    // Load data
    useEffect(() => {
        getDoctors();
        getDepartments();
    }, []);

    const [showDeleteModal, setShowDeleteModal] = useState(false);
    const [doctorToDelete, setDoctorToDelete] = useState(null);

    // API Call
    const getDoctors = async () => {
        try {
            const response = await API.get("/doctors");
            setDoctors(response.data);
        } catch (error) {
            console.log(error);
        }
    };

    const filteredDoctors = doctors.filter((doc) =>
        (doc.name || "").toLowerCase().includes(search.toLowerCase()) ||
        (doc.specialization || "").toLowerCase().includes(search.toLowerCase()) ||
        (doc.departmentName || "").toLowerCase().includes(search.toLowerCase()) ||
        String(doc.id).includes(search)
    );
    const totalDoctors = doctors.length;

    const cardiologyDoctors = doctors.filter(
        (doc) => doc.specialization === "Cardiology"
    ).length;

    const orthopedicsDoctors = doctors.filter((doc) => {
    const spec = (doc.specialization || "").trim().toLowerCase();

    return (
        spec.includes("orthopedic") ||
        spec.includes("orthopaedic") ||
        spec.includes("orthopedics")
    );
}).length;

    const totalDepartments = [
        ...new Set(
            doctors.map((doc) => doc.departmentName?.trim())
        ),
    ].length;

    const stats = [
        {
            title: "Total Doctors",
            count: totalDoctors,
            icon: <FaUserDoctor />,
            bg: "from-blue-500 to-blue-700",
        },
        {
            title: "Cardiologist",
            count: cardiologyDoctors,
            icon: <FaHeartPulse />,
            bg: "from-red-500 to-red-700",
        },

        {
            title: "Orthopedic Surgeon",
            count: orthopedicsDoctors,
            icon: <FaUserDoctor />,
            bg: "from-green-500 to-green-700",
        },

        {
            title: "Departments",
            count: totalDepartments,
            icon: <FaBuilding />,
            bg: "from-orange-500 to-orange-700",
        },
    ];

    return (

        <div>


            {/* Header */}
            <div className="flex justify-between items-center mb-6">
                <div>
                    <h1 className="text-3xl font-bold text-slate-800">
                        Doctors
                    </h1>

                    <p className="text-gray-500 mt-1">
                        Manage all hospital doctors
                    </p>
                </div>

                <button
                    onClick={() => {
                        setDoctor({
                            name: "",
                            specialization: "",
                            departmentId: "",
                            department: "",
                        });

                        setShowModal(true);
                    }}
                    className="
                            group
                            flex items-center gap-3
                            bg-gradient-to-r from-blue-600 via-indigo-600 to-purple-600
                            hover:from-blue-700 hover:via-indigo-700 hover:to-purple-700
                            text-white
                            px-6 py-3
                            rounded-2xl
                            text-base
                            font-extrabold
                            shadow-lg
                            hover:shadow-2xl
                            transition-all duration-300
                            hover:-translate-y-1 hover:scale-105
                            border border-white/20">

                    {/* 1️⃣ Plus Icon */}
                    <div className="
                            w-8 h-8
                            rounded-full
                            bg-white
                            text-blue-600
                            flex items-center justify-center
                            shadow-md
                            group-hover:rotate-90
                            transition-transform duration-300">

                        <FaPlus className="text-sm" />
                    </div>

                    {/*2️⃣  Text */}
                    <span className="tracking-wide">
                        Add Doctor
                    </span>



                    {/* 3️⃣ Doctor Picture/Icon */}
                    <div className="
                        w-9 h-9
                        rounded-full
                        bg-white/20
                        backdrop-blur-sm
                        flex items-center justify-center
                        border border-white/30">

                        <FaUserDoctor className="text-xl" />
                    </div>

                </button>
            </div>

            {/* Search */}
            <div className="relative w-72 mb-6">
                <FaSearch className="absolute left-3 top-1/2 -translate-y-1/2 text-gray-400 text-sm" />

                <input
                    type="text"
                    placeholder="Search Doctor..."
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                    className="w-full h-10 pl-10 pr-4 border border-gray-300 rounded-lg outline-none focus:ring-2 focus:ring-blue-300"
                />
            </div>

            {/* Statistics Cards */}

            <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-2 mb-5">

                {stats.map((item, index) => (

                    <div
                        key={index}
                        className={`
        bg-gradient-to-r ${item.bg}
        rounded-xl
        shadow-sm
        p-2.5
        text-white
        transition-all duration-300
        hover:scale-105
        hover:shadow-lg
        cursor-pointer
      `}
                    >

                        <div className="flex justify-between items-center">

                            {/* Left Side */}
                            <div>

                                <p className="text-[11px] opacity-90 font-medium uppercase tracking-wide">
                                    {item.title}
                                </p>

                                {/* Smaller Count */}
                                <h1 className="text-2xl font-bold mt-1">
                                    {item.count}
                                </h1>

                            </div>

                            {/* Smaller Icon */}
                            <div
                                className="
            w-10 h-10
            rounded-full
            bg-white/20
            flex items-center justify-center
            text-xl
            backdrop-blur-sm
          "
                            >
                                {item.icon}
                            </div>

                        </div>

                    </div>

                ))}

            </div>

            {/* Premium Doctor Table */}

            <div className="bg-white rounded-2xl shadow-md border border-gray-100 overflow-hidden">

                {/* Table Header */}

                <div className="flex justify-between items-center px-6 py-5 border-b">

                    <div>
                        <h2 className="text-xl font-bold text-slate-800">
                            Doctors List
                        </h2>

                        <p className="text-sm text-gray-500 mt-1">
                            Total Doctors : {filteredDoctors.length}
                        </p>
                    </div>


                    <div className="text-sm text-gray-400">
                        Hospital Management
                    </div>

                </div>

                {/* Table */}

                <div className="overflow-x-auto">

                    <table className="w-full">


                        <thead className="bg-slate-100">

                            <tr>

                                <th className="text-left px-6 py-4 text-sm font-semibold text-gray-600">
                                    Doctor
                                </th>

                                <th className="text-left px-6 py-4 text-sm font-semibold text-gray-600">
                                    Specialization
                                </th>

                                <th className="text-left px-6 py-4 text-sm font-semibold text-gray-600">
                                    Department
                                </th>

                                <th className="text-center px-6 py-4 text-sm font-semibold text-gray-600">
                                    Action
                                </th>

                            </tr>

                        </thead>

                        <tbody>

                            {filteredDoctors.map((doc) => {

                                return (
                                    <tr
                                        key={doc.id}
                                        className="border-t hover:bg-blue-50 transition duration-200"
                                    >

                                        {/* Doctor */}

                                        <td className="px-6 py-4">

                                            <div className="flex items-center gap-3">

                                                <div className="w-11 h-11 rounded-full overflow-hidden bg-blue-100 flex items-center justify-center">
                                                    {doc.image ? (
                                                        <img
                                                            src={doc.image}
                                                            alt={doc.name}
                                                            className="w-full h-full object-cover"
                                                        />
                                                    ) : (
                                                        <span className="text-blue-600 font-bold">
                                                            {doc.name.charAt(0)}
                                                        </span>
                                                    )}
                                                </div>

                                                <div>

                                                    <p className="font-semibold text-slate-800">
                                                        {doc.name}
                                                    </p>

                                                    <p className="text-sm text-gray-500">
                                                        ID : {doc.id}
                                                    </p>

                                                </div>

                                            </div>

                                        </td>

                                        {/* Specialization */}

                                        <td className="px-6 py-4 text-gray-700">
                                            <span className="px-3 py-1 rounded-full bg-green-100 text-green-700 text-sm font-medium">
                                                {doc.specialization || "No Specialization"}
                                            </span>
                                        </td>


                                        {/* Department */}

                                        <td className="px-6 py-4">
                                            <span className="px-3 py-1 rounded-full bg-green-100 text-green-700 text-sm font-medium">
                                                {doc.departmentName}
                                            </span>
                                        </td>

                                        {/* Action */}

                                        <td className="px-6 py-4">

                                            <div className="flex justify-center gap-3">

                                                <button
                                                    onClick={() => handleEditDoctor(doc)}
                                                    className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded-lg text-sm transition"
                                                >
                                                    Edit
                                                </button>

                                                <button
                                                    onClick={() => {
                                                        setDoctorToDelete(doc);
                                                        setShowDeleteModal(true);
                                                    }}
                                                    className="
                                                        bg-red-500 hover:bg-red-600
                                                        text-white px-4 py-2
                                                        rounded-lg text-sm
                                                        font-semibold
                                                        transition-all duration-200
                                                        hover:scale-105
                                                        shadow-md
                                                    "
                                                >
                                                    Delete
                                                </button>

                                            </div>

                                        </td>

                                    </tr>
                                );
                            })}

                            {filteredDoctors.length === 0 && (

                                <tr>

                                    <td
                                        colSpan="4" className="text-center py-8 text-gray-500 font-medium">
                                        No Doctors Found
                                    </td>

                                </tr>

                            )}


                        </tbody>

                    </table>

                </div>

            </div>

            {/* Add Doctor Modal */}
            {showModal && (
                <div className="fixed inset-0 bg-black/40 flex items-center justify-center z-50">
                    <div className="bg-white w-[500px] rounded-2xl shadow-xl p-6">
                        <div className="flex justify-between items-center mb-6">
                            <h2 className="text-2xl font-bold text-slate-800">
                                {isEdit ? "Edit Doctor" : "Add Doctor"}
                            </h2>

                            <button
                                onClick={() => {

                                    setShowModal(false);
                                    setIsEdit(false);

                                    setDoctor({
                                        name: "",
                                        specialization: "",
                                        departmentId: "",
                                        department: "",
                                    });

                                    setShowModal(false);
                                }}
                                className="text-3xl text-gray-500 hover:text-red-500"
                            >
                                &times;
                            </button>
                        </div>

                        <form onSubmit={handleSaveDoctor} className="space-y-4">

                            {/* Doctor Name */}
                            <div>
                                <label className="block mb-2 font-medium">
                                    Doctor Name
                                </label>

                                <input
                                    type="text"
                                    required
                                    value={doctor.name}
                                    onChange={(e) =>
                                        setDoctor({
                                            ...doctor,
                                            name: e.target.value,
                                        })
                                    }
                                    className="w-full border border-gray-300 rounded-lg px-4 py-2 outline-none focus:ring-2 focus:ring-blue-300"
                                    placeholder="Enter doctor name"
                                />
                            </div>

                            {/* Specialization */}

                            <div>
                                <label className="block mb-2 font-medium">
                                    Specialization
                                </label>

                                <input
                                    type="text"
                                    required
                                    value={doctor.specialization}
                                    onChange={(e) =>
                                        setDoctor({
                                            ...doctor,
                                            specialization: e.target.value,
                                        })
                                    }
                                    placeholder="Enter specialization"
                                    className="w-full border border-gray-300 rounded-lg px-4 py-2 outline-none focus:ring-2 focus:ring-blue-300"
                                />
                            </div>

                            {/* Department */}
                            <div>
                                <label className="block mb-2 font-medium">
                                    Department
                                </label>

                                <Select
                                    options={departmentOptions}
                                    placeholder="Select Department"
                                    value={
                                        departmentOptions.find(
                                            (option) => option.value === doctor.departmentId
                                        ) || null
                                    }
                                    onChange={(selectedOption) =>
                                        setDoctor({
                                            ...doctor,
                                            departmentId: selectedOption ? selectedOption.value : ""
                                        })
                                    }
                                    isSearchable
                                    className="text-sm"
                                />
                            </div>

                            {/* Buttons */}
                            <div className="flex justify-end gap-3 pt-4">

                                <button
                                    type="button"
                                    onClick={() => {
                                        setDoctor({
                                            id: "",
                                            name: "",
                                            specialization: "",
                                            departmentId: ""
                                        });

                                        setShowModal(false);
                                    }}
                                    className="bg-gray-300 hover:bg-gray-400 px-5 py-2 rounded-lg">
                                    Cancel
                                </button>

                                <button
                                    type="submit"
                                    className="bg-blue-600 hover:bg-blue-700 text-white px-5 py-2 rounded-lg"
                                >
                                    {isEdit ? "Update" : "Save"}
                                </button>

                            </div>

                        </form>

                    </div>
                </div>
            )}

            {showDeleteModal && (
                <div className="fixed inset-0 bg-black/50 backdrop-blur-sm flex items-center justify-center z-50">

                    <div className="bg-white rounded-2xl shadow-2xl w-full max-w-md p-6 animate-fadeIn">

                        {/* Icon */}
                        <div className="flex justify-center mb-4">
                            <div className="w-16 h-16 rounded-full bg-red-100 flex items-center justify-center">
                                <svg
                                    className="w-8 h-8 text-red-600"
                                    fill="none"
                                    stroke="currentColor"
                                    viewBox="0 0 24 24"
                                >
                                    <path
                                        strokeLinecap="round"
                                        strokeLinejoin="round"
                                        strokeWidth={2}
                                        d="M12 9v2m0 4h.01M10.29 3.86l-7.5 13A1 1 0 003.66 18h16.68a1 1 0 00.87-1.5l-7.5-13a1 1 0 00-1.74 0z"
                                    />
                                </svg>
                            </div>
                        </div>

                        {/* Title */}
                        <h2 className="text-2xl font-bold text-center text-gray-800 mb-2">
                            Are You Sure?
                        </h2>

                        {/*  Message  */}
                        <p className="text-center text-gray-600 mb-6 leading-relaxed">
                            You are about to delete{" "}

                            <span className="font-bold text-navy-600">
                                {doctorToDelete?.name}
                            </span>

                            . This action cannot be undone.
                        </p>

                        {/* Buttons */}
                        <div className="flex justify-center gap-3">

                            {/* Cancel */}
                            <button
                                onClick={() => {
                                    setShowDeleteModal(false);
                                    setDoctorToDelete(null);
                                }}
                                className="
                                    px-5 py-2.5
                                    rounded-xl
                                    bg-gray-200 hover:bg-gray-300
                                    text-gray-800
                                    font-semibold
                                    transition">
                                Cancel
                            </button>

                            {/* Confirm Delete */}
                            <button
                                onClick={async () => {
                                    await handleDeleteDoctor(doctorToDelete.id);
                                    setShowDeleteModal(false);
                                    setDoctorToDelete(null);
                                }}
                                className="
                        px-5 py-2.5
                        rounded-xl
                        bg-red-600 hover:bg-red-700
                        text-white
                        font-semibold
                        shadow-lg
                        transition-all duration-200
                        hover:scale-105
                    "
                            >
                                Yes, Delete
                            </button>

                        </div>

                    </div>

                </div>
            )}
        </div>

    );
}

export default Doctors;
