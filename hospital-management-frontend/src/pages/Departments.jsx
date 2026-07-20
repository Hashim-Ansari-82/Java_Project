import { useEffect, useState } from 'react';
import { FaPlus, FaSearch, FaEdit, FaTrash, FaBuilding } from 'react-icons/fa';
import API from '../api/axiosConfig';

function Departments() {
  const [departments, setDepartments] = useState([]);
  const [search, setSearch] = useState('');
  const [showModal, setShowModal] = useState(false);
  const [isEdit, setIsEdit] = useState(false);
  const [error, setError] = useState('');

  const [department, setDepartment] = useState({
    id: null,
    name: '',
  });

  // ================= LOAD DATA =================
  useEffect(() => {
    getDepartments();
  }, []);

  const getDepartments = async () => {
    try {
      const response = await API.get('/departments');
      setDepartments(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  // ================= SAVE / UPDATE =================
  const handleSave = async (e) => {
    e.preventDefault();
    setError('');

    // Frontend validation
    if (!department.name.trim()) {
      setError('Department name is required');
      return;
    }

    try {
      if (isEdit) {
        await API.put(`/departments/${department.id}`, {
          name: department.name,
        });
      } else {
        await API.post('/departments', {
          name: department.name,
        });
      }

      setShowModal(false);
      resetForm();
      getDepartments();
    } catch (error) {
      console.error(error);

      // Backend validation message
      if (error.response?.status === 400) {
        setError('You cannot save/update without department name');
      } else {
        setError('Server error occurred');
      }
    }
  };

  // ================= DELETE =================
  const handleDelete = async (id) => {
    if (!window.confirm('Delete this department?')) return;

    try {
      await API.delete(`/departments/${id}`);
      getDepartments();
    } catch (error) {
      console.error(error);
    }
  };

  // ================= EDIT =================
  const handleEdit = (dept) => {
    setDepartment(dept);
    setIsEdit(true);
    setShowModal(true);
    setError('');
  };

  // ================= RESET =================
  const resetForm = () => {
    setDepartment({
      id: null,
      name: '',
    });
    setIsEdit(false);
    setError('');
  };

  // ================= SEARCH FILTER =================
  const filteredDepartments = departments.filter((dept) =>
    dept.name.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <div className="min-h-screen bg-slate-50 p-6">
      {/* Header */}
      <div className="flex flex-col md:flex-row md:items-center md:justify-between gap-4 mb-6">
        <div>
          <h1 className="text-3xl font-bold text-slate-800">
            Department Management
          </h1>
          <p className="text-gray-500 mt-1">
            Manage hospital departments and specializations
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
          Add Department
        </button>
      </div>

      {/* Search */}
      <div className="bg-white rounded-2xl shadow-sm p-4 mb-6">
        <div className="relative">
          <FaSearch className="absolute left-4 top-1/2 -translate-y-1/2 text-gray-400" />
          <input
            type="text"
            placeholder="Search departments..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            className="w-full pl-12 pr-4 py-3 border border-gray-200 rounded-xl focus:ring-2 focus:ring-blue-500 outline-none"
          />
        </div>
      </div>

       {/* Department Stats */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-6">

        {/* Total Departments */}
        <div className="bg-gradient-to-br from-blue-500 to-blue-900 
      text-white rounded-xl p-4 shadow-lg">

          <div className="flex justify-between items-center">

            <div>
              <p className="text-sm text-blue-100">
                Total Departments
              </p>

              <h2 className="text-3xl font-bold mt-1">
                {departments.length}
              </h2>
            </div>

            <div className="w-10 h-10 rounded-full 
          bg-blue-400/40 flex items-center justify-center text-xl">
              <FaBuilding />
            </div>

          </div>

        </div>


        {/* Active Departments */}
        <div className="bg-gradient-to-br from-emerald-500 to-emerald-900 
      text-white rounded-xl p-4 shadow-lg">

          <div className="flex justify-between items-center">

            <div>
              <p className="text-sm text-emerald-100">
                Active Departments
              </p>

              <h2 className="text-3xl font-bold mt-1">
                {departments.length}
              </h2>
            </div>

            <div className="w-10 h-10 rounded-full 
          bg-emerald-400/40 flex items-center justify-center text-xl">
              🏥
            </div>

          </div>

        </div>


        {/* Specializations */}
        <div className="bg-gradient-to-br from-purple-500 to-purple-900 
      text-white rounded-xl p-4 shadow-lg">

          <div className="flex justify-between items-center">

            <div>
              <p className="text-sm text-purple-100">
                Specializations
              </p>

              <h2 className="text-3xl font-bold mt-1">
                {departments.length}
              </h2>
            </div>

            <div className="w-10 h-10 rounded-full 
          bg-purple-400/40 flex items-center justify-center text-xl">
              🩺
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
                <th className="text-left px-6 py-4 font-semibold text-slate-700">
                  Department
                </th>
                <th className="text-center px-6 py-4 font-semibold text-slate-700">
                  Actions
                </th>
              </tr>
            </thead>

            <tbody>
              {filteredDepartments.map((dept) => (
                <tr
                  key={dept.id}
                  className="border-t hover:bg-blue-50 transition"
                >
                  <td className="px-6 py-4">
                    <div className="flex items-center gap-3">
                      <div className="w-12 h-12 rounded-full bg-blue-100 flex items-center justify-center text-blue-600">
                        <FaBuilding />
                      </div>

                      <div>
                        <p className="font-semibold text-slate-800">
                          {dept.name}
                        </p>
                        <p className="text-sm text-gray-500">
                          Department ID: {dept.id}
                        </p>
                      </div>
                    </div>
                  </td>

                  <td className="px-6 py-4">
                    <div className="flex items-center justify-center gap-3">
                      <button
                        onClick={() => handleEdit(dept)}
                        className="p-2 rounded-lg bg-blue-100 text-blue-600 hover:bg-blue-200 transition"
                      >
                        <FaEdit />
                      </button>

                      <button
                        onClick={() => handleDelete(dept.id)}
                        className="p-2 rounded-lg bg-red-100 text-red-600 hover:bg-red-200 transition"
                      >
                        <FaTrash />
                      </button>
                    </div>
                  </td>
                </tr>
              ))}

              {filteredDepartments.length === 0 && (
                <tr>
                  <td colSpan="2" className="text-center py-10 text-gray-500">
                    No departments found
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
          <div className="bg-white rounded-2xl w-full max-w-md p-6 shadow-2xl">
            <div className="flex items-center justify-between mb-6">
              <h2 className="text-2xl font-bold text-slate-800">
                {isEdit ? 'Update Department' : 'Add Department'}
              </h2>

              <button
                onClick={() => setShowModal(false)}
                className="text-gray-400 hover:text-gray-600 text-2xl"
              >
                ×
              </button>
            </div>

            {/* Error Message */}
            {error && (
              <div className="bg-red-100 border border-red-300 text-red-700 px-4 py-3 rounded-xl mb-4">
                {error}
              </div>
            )}

            <form onSubmit={handleSave} className="space-y-5">
              <div>
                <label className="block text-sm font-medium text-slate-700 mb-2">
                  Department Name
                </label>

                <input
                  type="text"
                  value={department.name}
                  onChange={(e) =>
                    setDepartment({ ...department, name: e.target.value })
                  }
                  placeholder="e.g. Cardiology, Neurology, Orthopedics"
                  className="w-full border border-gray-300 rounded-xl px-4 py-3 focus:ring-2 focus:ring-blue-500 outline-none"
                />
              </div>

              <div className="flex justify-end gap-3 pt-2">
                <button
                  type="button"
                  onClick={() => setShowModal(false)}
                  className="px-5 py-3 border border-gray-300 rounded-xl hover:bg-gray-50 transition"
                >
                  Cancel
                </button>

                <button
                  type="submit"
                  className="px-5 py-3 bg-blue-600 hover:bg-blue-700 text-white rounded-xl shadow-lg transition"
                >
                  {isEdit ? 'Update Department' : 'Save Department'}
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}

export default Departments;