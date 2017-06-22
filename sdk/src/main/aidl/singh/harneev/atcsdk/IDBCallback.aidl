// IDBCallback.aidl
package singh.harneev.atcsdk;

import singh.harneev.atcsdk.school.School;

// The prefix is used as a hint for performance optimization.
// "in" means this is just an input parameter for the method, and it won't be modified inside this method.
// "out" mean it won't be read, but modified. That's why it was initialized with default values,
// because system does not expect it to be read anyway.
// "inout" means it will be both read and modified. Modified values will be sent back to the service.

interface IDBCallback {

    void handleSchoolDetail(in List<School> schoolList);
}
