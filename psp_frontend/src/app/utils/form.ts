import { Observable } from 'rxjs'

export interface FormConfig {
  [control: string]: {
    type?: 'text' | 'password' | 'select' | 'multi-select'
    validation?: 'none' | 'required'
    options?: Observable<unknown[]>
    optionValue?: string
    optionKey?: string
    hidding?: {
      field: string
      values: string[]
    }
    disabled?: boolean
  }
}

export interface FormStyle {
  width?: string
  'margin-top'?: string
}
