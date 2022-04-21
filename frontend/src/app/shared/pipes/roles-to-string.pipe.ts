import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'rolesToString'
})
export class RolesToStringPipe implements PipeTransform {

  transform(roles: []): string {
    let rolesAsString = '';
    roles.forEach((role: any) => {
      rolesAsString += role.name + ' ';
    })

    return rolesAsString;
  }

}
