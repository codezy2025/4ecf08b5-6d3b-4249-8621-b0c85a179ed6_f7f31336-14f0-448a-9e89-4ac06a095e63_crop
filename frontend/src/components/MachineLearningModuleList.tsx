/**
 * 🏗️  DEVELOPMENT GUIDE - Machine Learning Module List Component
 * 
 * 📋 Original Requirements: **Module-Level Documentation Template with Mermaid Diagrams**

**1. Module Name & Overview**

* **Module Name:** Machine Learning Module
* **Summary:** This module is responsible for training and evaluating machine learning models using historical data.
* **Business Domain Context:** The machine learning module is used in the Crop Recommendation System to provide personalized crop recommendations based on various environmental and soil conditions.
* **Owner/Team:** The machine learning module is owned and maintained by the Data Science team.

**2. Public Interfaces / APIs**

* **Exposed Methods/Functions/Classes:**
	+ `train_model()`: Trains a machine learning model using historical data.
	+ `evaluate_model()`: Evaluates the performance of a trained machine learning model.
	+ `make_recommendations()`: Generates crop recommendations based on the trained model and input parameters.
* **REST/RPC/GraphQL Endpoints (if applicable):** None
* **Parameters and Response Structures:**
	+ `train_model()`: Takes in historical data and returns a trained machine learning model.
	+ `evaluate_model()`: Takes in a trained machine learning model and returns its performance metrics.
	+ `make_recommendations()`: Takes in input parameters and returns crop recommendations.
* **Example Usage / Curl or Code Snippets:**
```python
# Train a machine learning model
model = train_model(historical_data)

# Evaluate the performance of the model
performance_metrics = evaluate_model(model)

# Generate crop recommendations
recommendations = make_recommendations(input_parameters)
```

**3. Dependencies**

* **Internal Dependencies (calls to other modules or services):**
	+ `crop_recommendation_system`: The machine learning module interacts with the Crop Recommendation System to provide personalized crop recommendations.
* **External Libraries or Packages:**
	+ `scikit-learn`: The machine learning module uses the scikit-learn library to train and evaluate machine learning models.
	+ `pandas`: The machine learning module uses the pandas library to handle and manipulate data.
* **System/Service Dependencies:**
	+ `database`: The machine learning module interacts with a database to store and retrieve historical data.

**4. Configuration**

* **Environment Variables Used:**
	+ `MODEL_TYPE`: The type of machine learning model to train (e.g., random forest, neural network).
	+ `HISTORICAL_DATA_PATH`: The path to the historical data file.
* **JSON/YAML/TOML Config Examples:**
```json
{
  "model_type": "random_forest",
  "historical_data_path": "/path/to/historical/data.csv"
}
```

**5. Data Models / Schema**

* **Main Data Models or Classes:**
	+ `MachineLearningModel`: Represents a trained machine learning model.
	+ `CropRecommendation`: Represents a crop recommendation.
* **Table/Collection Structure (if DB-driven):**
	+ `machine_learning_models`: A table storing trained machine learning models.
	+ `crop_recommendations`: A table storing crop recommendations.
* **Field Types and Validations:**
	+ `machine_learning_models`: `id` (primary key), `model_type`, `historical_data_path`.
	+ `crop_recommendations`: `id` (primary key), `crop_name`, `input_parameters`.

**6. Workflow & Logic**

* **Main Operations/Workflows (e.g., “how a user is created”):**
	1. Train a machine learning model using historical data.
	2. Evaluate the performance of the model.
	3. Generate crop recommendations based on the trained model and input parameters.
* **Business Rules:**
	+ The machine learning module must be trained using historical data.
	+ The model must be evaluated for its performance.
	+ Crop recommendations must be generated based on the trained model and input parameters.

**7. Sequence & Interaction Flow**

* **Request Lifecycle or Internal Control Flow:**
	1. Receive input parameters from the user.
	2. Train a machine learning model using historical data.
	3. Evaluate the performance of the model.
	4. Generate crop recommendations based on the trained model and input parameters.
* **How this Module Interacts with Other Services/Modules:**
	+ The machine learning module interacts with the Crop Recommendation System to provide personalized crop recommendations.
* **Async Events, Queues, or Job Triggers (if any):** None

**8. Error Handling**

* **List of Known Errors and Exceptions:**
	+ `ModelTrainingError`: Raised when training a machine learning model fails.
	+ `ModelEvaluationError`: Raised when evaluating a machine learning model fails.
	+ `CropRecommendationError`: Raised when generating crop recommendations fails.
* **How Errors are Caught and Handled:**
	+ Errors are caught and handled using try-except blocks.
	+ Errors are logged and reported to the user.

**9. Security Considerations**

* **Auth Checks Performed:**
	+ The machine learning module performs authentication checks to ensure that only authorized users can access the system.
* **Role-Based Access or Token Validation:**
	+ The machine learning module uses role-based access control to ensure that only authorized users can access the system.
* **Data Encryption or Sensitive Data Protection:**
	+ The machine learning module uses encryption to protect sensitive data.

**10. Testing**

* **Unit and Integration Test Summary:**
	+ The machine learning module has been unit tested and integrated tested.
* **Coverage Reports (if available):**
	+ The machine learning module has a high test coverage.
* **How to Run Tests for this Module:**
	+ Run the tests using the `pytest` command.

**11. Logging & Observability**

* **Logs Emitted (levels and messages):**
	+ The machine learning module emits logs at the INFO level.
* **Metrics Generated (counters, gauges, histograms):**
	+ The machine learning module generates metrics using the Prometheus library.
* **Dashboards or Alerts Associated:**
	+ The machine learning module is associated with a dashboard and alert system.

**12. Performance**

* **Known Performance Issues or Constraints:**
	+ The machine learning module may experience performance issues when training large models.
* **Benchmarks, Profiling Tools, or Load Test Results:**
	+ The machine learning module has been benchmarked and profiled using the `line_profiler` library.
* **Recommendations for Scaling:**
	+ The machine learning module can be scaled by using a distributed computing framework.

**13. How to Extend or Modify**

* **Tips for Adding New Features Safely:**
	+ Add new features by creating a new branch and testing the changes thoroughly.
* **Key Extension Points:**
 * 
 * 🚀 Enhancement Ideas:
 * - Add search/filter functionality
 * - Implement sorting for all columns
 * - Add bulk operations (delete, update status)
 * - Include export functionality (CSV, PDF)
 * - Add infinite scrolling or virtual scrolling
 * - Implement row selection with checkboxes
 * 
 * 💡 Props to Consider Adding:
 * - searchTerm?: string
 * - filters?: Record<string, any>
 * - sortConfig?: { key: string, direction: 'asc' | 'desc' }
 * - isLoading?: boolean
 * - onBulkAction?: (action: string, ids: string[]) => void
 * 
 * 🔧 Libraries to Consider:
 * - @tanstack/react-table for advanced features
 * - react-window for virtualization
 * - fuse.js for fuzzy search
 */

import React from 'react';
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table';
import { Button } from '@/components/ui/button';
import { MachineLearningModule } from '../types/Machine Learning ModuleTypes';

interface MachineLearningModuleListProps {
  data: MachineLearningModule[];
  onEdit: (module: MachineLearningModule) => void;
  onDelete: (id: string) => void;
}

const MachineLearningModuleList: React.FC<MachineLearningModuleListProps> = ({ data, onEdit, onDelete }) => {
  return (
    <div className="w-full overflow-x-auto">
      <Table>
        <TableHeader>
          <TableRow>
            <TableHead className="w-1/4">Module Name</TableHead>
            <TableHead className="w-1/4">Model Type</TableHead>
            <TableHead className="w-1/4">Status</TableHead>
            <TableHead className="w-1/4">Actions</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {data.map((module) => (
            <TableRow key={module.id}>
              <TableCell className="font-medium">{module.name}</TableCell>
              <TableCell>{module.modelType}</TableCell>
              <TableCell>
                <span
                  className={`inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium ${
                    module.status === 'Trained'
                      ? 'bg-green-100 text-green-800'
                      : module.status === 'Training'
                      ? 'bg-yellow-100 text-yellow-800'
                      : 'bg-red-100 text-red-800'
                  }`}
                >
                  {module.status}
                </span>
              </TableCell>
              <TableCell>
                <div className="flex gap-2">
                  <Button variant="outline" size="sm" onClick={() => onEdit(module)}>
                    Edit
                  </Button>
                  <Button variant="destructive" size="sm" onClick={() => onDelete(module.id)}>
                    Delete
                  </Button>
                </div>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
};

export default MachineLearningModuleList;