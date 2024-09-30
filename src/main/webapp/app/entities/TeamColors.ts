import { Teams } from './Teams';
import { TeamColorTypes } from './TeamColorTypes';
export interface TeamColors {
    teamColorId: number;
    alpha?: number;
    rgbBlue?: number;
    rgbGreen?: number;
    rgbRed?: number;
    teamId: number;
    hexColor?: string;
    teamColorTypeCode: string;
    teamColorTypes?: TeamColorTypes;
    teams?: Teams;
}
