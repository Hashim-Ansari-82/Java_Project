import { useEffect, useState } from 'react';
import { FaPlus, FaSearch, FaEdit, FaTrash, FaUserInjured } from 'react-icons/fa';
import API from '../api/axiosConfig';

function Patients() {
    const [patients, setPatients] = useState([]);
    const [search, setSearch] = useState('');
    const [showModal, setShowModal] = useState(false);
    const [isEdit, setIsEdit] = useState(false);

    const [patient, setPatient] = useState({
        id: null,
        name: '',
        age: '',
        gender: '',
        mobile: '',
    });

    // Load data
    useEffect(() => {
        getPatients();
    }, []);

    const getPatients = async () => {
        try {
            const response = await API.get('/patients');
            setPatients(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    // Save / Update
    const handleSave = async (e) => {
        e.preventDefault();

        try {
            if (isEdit) {
                await API.put(`/patients/${patient.id}`, patient);
            } else {
                await API.post('/patients', patient);
            }

            setShowModal(false);
            resetForm();
            getPatients();
        } catch (error) {
            console.error(error);

            // Backend validation error
            if (error.response?.status === 400) {
                const validationErrors = error.response.data;

                // Agar mobile field ka error hai
                if (validationErrors.mobile) {
                    alert(`You cannot update: ${validationErrors.mobile}`);
                    return;
                }

                // Generic validation message
                alert('You cannot update because the data is invalid.');
            } else {
                alert('Server error occurred');
            }
        }
    };

    // Delete
    const handleDelete = async (id) => {
        if (!window.confirm('Delete this patient?')) return;

        try {
            await API.delete(`/patients/${id}`);
            getPatients();
        } catch (error) {
            console.error(error);
        }
    };

    // Edit
    const handleEdit = (p) => {
        setPatient(p);
        setIsEdit(true);
        setShowModal(true);
    };

    // Reset
    const resetForm = () => {
        setPatient({
            id: null,
            name: '',
            age: '',
            gender: '',
            mobile: '',
        });
        setIsEdit(false);
    };

    // Search filter
    const filteredPatients = patients.filter((p) =>
        p.name.toLowerCase().includes(search.toLowerCase())
    );

    return (
        <div className="min-h-screen bg-slate-50 p-6">
            {/* Header */}
            <div className="flex flex-col md:flex-row md:items-center md:justify-between gap-4 mb-6">
                <div>
                    <h1 className="text-3xl font-bold text-slate-800">Patient Management</h1>
                    <p className="text-gray-500 mt-1">
                        Manage hospital patient records and registrations
                    </p>
                </div>

                <button
                    onClick={() => {
                        resetForm();
                        setShowModal(true);
                    }}
                    className="bg-blue-600 hover:bg-blue-700 text-white px-5 py-3 rounded-xl flex items-center gap-2 shadow-lg transition"
                >
                    <FaPlus />
                    Add Patient
                </button>
            </div>

            {/* Search */}
            <div className="bg-white rounded-2xl shadow-sm p-4 mb-6">
                <div className="relative">
                    <FaSearch className="absolute left-4 top-1/2 -translate-y-1/2 text-gray-400" />
                    <input
                        type="text"
                        placeholder="Search patients by name..."
                        value={search}
                        onChange={(e) => setSearch(e.target.value)}
                        className="w-full pl-12 pr-4 py-3 border border-gray-200 rounded-xl focus:ring-2 focus:ring-blue-500 outline-none"
                    />
                </div>
            </div>
            {/* Stats Cards */}
            <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-6">

                {/* Total Patients */}
                <div className="bg-gradient-to-br from-blue-500 to-blue-900 
        text-white rounded-xl p-4 shadow-lg 
        hover:scale-105 transition cursor-pointer">

                    <div className="flex justify-between items-center">

                        <div>
                            <p className="text-sm text-blue-100">
                                Total Patients
                            </p>

                            <h2 className="text-3xl font-bold mt-1">
                                {patients.length}
                            </h2>
                        </div>

                        <div className="w-10 h-10 rounded-full 
                bg-blue-400/40 flex items-center justify-center text-xl">
                            <FaUserInjured />
                        </div>

                    </div>

                </div>


                {/* Average Wait Time */}
                <div className="bg-gradient-to-br from-emerald-400 to-emerald-900 
        text-white rounded-xl p-4 shadow-lg 
        hover:scale-105 transition cursor-pointer">

                    <div className="flex justify-between items-center">

                        <div>
                            <p className="text-sm text-emerald-100">
                                Avg. Wait Time
                            </p>

                            <h2 className="text-3xl font-bold mt-1">
                                12 min
                            </h2>
                        </div>

                        <div className="w-10 h-10 rounded-full 
                bg-emerald-300/40 flex items-center justify-center text-xl">
                            ⏱️
                        </div>

                    </div>

                </div>


                {/* Critical Cases */}
                <div className="bg-gradient-to-br from-rose-500 to-red-900 
        text-white rounded-xl p-4 shadow-lg 
        hover:scale-105 transition cursor-pointer">

                    <div className="flex justify-between items-center">

                        <div>
                            <p className="text-sm text-rose-100">
                                Critical Cases
                            </p>

                            <h2 className="text-3xl font-bold mt-1">
                                4
                            </h2>
                        </div>

                        <div className="w-10 h-10 rounded-full 
                bg-rose-400/40 flex items-center justify-center text-xl">
                            🚑
                        </div>

                    </div>

                </div>

            </div>

            {/* Table */}
            <div className="bg-white rounded-2xl shadow-sm overflow-hidden">
                <div className="overflow-x-auto">
                    <table className="w-full">
                        <thead className="bg-slate-100">
                            <tr>
                                <th className="text-left px-6 py-4 font-semibold text-slate-700">Patient</th>
                                <th className="text-left px-6 py-4 font-semibold text-slate-700">Age</th>
                                <th className="text-left px-6 py-4 font-semibold text-slate-700">Gender</th>
                                <th className="text-left px-6 py-4 font-semibold text-slate-700">Mobile</th>
                                <th className="text-center px-6 py-4 font-semibold text-slate-700">Actions</th>
                            </tr>
                        </thead>

                        <tbody>
                            {filteredPatients.map((p) => (
                                <tr key={p.id} className="border-t hover:bg-blue-50 transition">
                                    <td className="px-6 py-4">
                                        <div className="flex items-center gap-3">
                                            <div className="w-11 h-11 rounded-full bg-blue-100 flex items-center justify-center text-blue-600">
                                                <FaUserInjured />
                                            </div>
                                            <div>
                                                <p className="font-semibold text-slate-800">{p.name}</p>
                                                <p className="text-sm text-gray-500">Patient ID: {p.id}</p>
                                            </div>
                                        </div>
                                    </td>

                                    <td className="px-6 py-4">{p.age}</td>
                                    <td className="px-6 py-4">
                                        <span className="px-3 py-1 rounded-full text-sm bg-blue-100 text-blue-700">
                                            {p.gender}
                                        </span>
                                    </td>
                                    <td className="px-6 py-4">{p.mobile}</td>

                                    <td className="px-6 py-4">
                                        <div className="flex items-center justify-center gap-3">
                                            <button
                                                onClick={() => handleEdit(p)}
                                                className="p-2 rounded-lg bg-blue-100 text-blue-600 hover:bg-blue-200 transition"
                                            >
                                                <FaEdit />
                                            </button>

                                            <button
                                                onClick={() => handleDelete(p.id)}
                                                className="p-2 rounded-lg bg-red-100 text-red-600 hover:bg-red-200 transition"
                                            >
                                                <FaTrash />
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                            ))}

                            {filteredPatients.length === 0 && (
                                <tr>
                                    <td colSpan="5" className="text-center py-10 text-gray-500">
                                        No patients found
                                    </td>
                                </tr>
                            )}
                        </tbody>
                    </table>
                </div>
            </div>

            {/* Modal */}
            {showModal && (
                <div className="fixed inset-0 bg-black/40 flex items-center justify-center z-50 p-4">
                    <div className="bg-white rounded-2xl w-full max-w-lg p-6 shadow-2xl">
                        <div className="flex items-center justify-between mb-6">
                            <h2 className="text-2xl font-bold text-slate-800">
                                {isEdit ? 'Update Patient' : 'Add Patient'}
                            </h2>

                            <button
                                onClick={() => setShowModal(false)}
                                className="text-gray-400 hover:text-gray-600 text-2xl"
                            >
                                ×
                            </button>
                        </div>

                        <form onSubmit={handleSave} className="space-y-4">
                            <div>
                                <label className="block text-sm font-medium mb-2">Full Name</label>
                                <input
                                    type="text"
                                    value={patient.name}
                                    onChange={(e) =>
                                        setPatient({ ...patient, name: e.target.value })
                                    }
                                    className="w-full border border-gray-300 rounded-xl px-4 py-3 focus:ring-2 focus:ring-blue-500 outline-none"
                                    required
                                />
                            </div>

                            <div className="grid grid-cols-2 gap-4">
                                <div>
                                    <label className="block text-sm font-medium mb-2">Age</label>
                                    <input
                                        type="number"
                                        value={patient.age}
                                        onChange={(e) =>
                                            setPatient({ ...patient, age: e.target.value })
                                        }
                                        className="w-full border border-gray-300 rounded-xl px-4 py-3 focus:ring-2 focus:ring-blue-500 outline-none"
                                        required
                                    />
                                </div>

                                <div>
                                    <label className="block text-sm font-medium mb-2">Gender</label>
                                    <select
                                        value={patient.gender}
                                        onChange={(e) =>
                                            setPatient({ ...patient, gender: e.target.value })
                                        }
                                        className="w-full border border-gray-300 rounded-xl px-4 py-3 focus:ring-2 focus:ring-blue-500 outline-none"
                                        required
                                    >
                                        <option value="">Select</option>
                                        <option value="Male">Male</option>
                                        <option value="Female">Female</option>
                                        <option value="Other">Other</option>
                                    </select>
                                </div>
                            </div>

                            <div>
                                <label className="block text-sm font-medium mb-2">Mobile Number</label>
                                <input
                                    type="text"
                                    value={patient.mobile}
                                    onChange={(e) =>
                                        setPatient({ ...patient, mobile: e.target.value })
                                    }
                                    className="w-full border border-gray-300 rounded-xl px-4 py-3 focus:ring-2 focus:ring-blue-500 outline-none"
                                    required
                                />
                            </div>

                            <div className="flex justify-end gap-3 pt-4">
                                <button
                                    type="button"
                                    onClick={() => setShowModal(false)}
                                    className="px-5 py-3 border border-gray-300 rounded-xl hover:bg-gray-50"
                                >
                                    Cancel
                                </button>

                                <button
                                    type="submit"
                                    className="px-5 py-3 bg-blue-600 hover:bg-blue-700 text-white rounded-xl shadow-lg"
                                >
                                    {isEdit ? 'Update Patient' : 'Save Patient'}
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            )}
        </div>
    );
}

export default Patients;